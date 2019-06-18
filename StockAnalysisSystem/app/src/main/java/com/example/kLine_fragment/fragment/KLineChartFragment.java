package com.example.kLine_fragment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.stockanalysissystem.R;
import com.example.util.Util;
import com.vinsonguo.klinelib.chart.KLineView;
import com.vinsonguo.klinelib.model.HisData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KLineChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KLineChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KLineChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private KLineView mKLineView;
    private int mDay;
    private String gid;
    private OnFragmentInteractionListener mListener;

    public KLineChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param day Parameter 1.
     * @return A new instance of fragment KLineChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KLineChartFragment newInstance(int day,String id) {
        KLineChartFragment fragment = new KLineChartFragment();
        Bundle args = new Bundle();
        args.putInt("day",day);
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDay=getArguments().getInt("day");
        gid=getArguments().getString("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_kline_chart, container, false);
        mKLineView=v.findViewById(R.id.kline);
        RadioGroup rgIndex=v.findViewById(R.id.rg_index);
        mKLineView.setDateFormat("yyyy-MM-dd");
        rgIndex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cb_vol) {
                    showVolume();
                } else if (checkedId == R.id.cb_macd) {
                    showMacd();
                } else if (checkedId == R.id.cb_kdj) {
                    showKdj();
                } else if (checkedId == R.id.cb_rsi) {
                    showRsi();
                }else if (checkedId == R.id.cb_boll) {
                    showBoll();
                }else if (checkedId == R.id.cb_wr) {
                    showWr();
                }else if (checkedId == R.id.cb_expma) {
                    showExpma();
                }else if (checkedId == R.id.cb_roc) {
                    showRoc();
                }else if (checkedId == R.id.cb_dmi) {
                    showDmi();
                }
                //cb_rsi
            }
        });
        initData();
        ((RadioButton)rgIndex.getChildAt(0)).setChecked(true);//给RadioGroup设置默认项

        return v;
    }

    public void showVolume(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showVolume();
            }
        });
    }

    public void showMacd(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showMacd();
            }
        });
    }

    public void showKdj(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showKdj();
            }
        });
    }

    public void showRsi(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showRsi();
            }
        });
    }

    public void showBoll(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showBoll();
            }
        });
    }
    public void showWr(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showWr();
            }
        });
    }
    public void showExpma(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showExpma();
            }
        });
    }
    public void showRoc(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showRoc();
            }
        });
    }
    public void showDmi(){
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showDmi();
            }
        });
    }
    protected void initData() {
        final List<HisData> hisData = Util.getK(getContext(), mDay,gid);
        mKLineView.initData(hisData);
        mKLineView.setLimitLine();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
