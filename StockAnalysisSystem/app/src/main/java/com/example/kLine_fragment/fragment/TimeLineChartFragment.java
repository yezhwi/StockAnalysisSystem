package com.example.kLine_fragment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.util.Util;
import com.vinsonguo.klinelib.chart.TimeLineView;
import com.vinsonguo.klinelib.model.HisData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeLineChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeLineChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeLineChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;


    //
    private TimeLineView mTimeLineView;
    private int mType;
    private String gid;
    public TimeLineChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @int type
     * @return A new instance of fragment TimeLineChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeLineChartFragment newInstance(int type,String id) {
        TimeLineChartFragment fragment = new TimeLineChartFragment();
        Bundle args = new Bundle();
        args.putInt("type",type);
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType=getArguments().getInt("type");
        gid=getArguments().getString("id");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_time_line_chart, container, false);
        mTimeLineView=new TimeLineView(getContext());
        mTimeLineView.setDateFormat("HH:mm");
        int count=241;
        mTimeLineView.setCount(count,count,count);
        initData();
        return mTimeLineView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void initData() {
        final List<HisData> hisData = Util.get1Day(getContext(),gid);
        mTimeLineView.setLastClose(hisData.get(0).getClose());
        mTimeLineView.initData(hisData);
    }
}
