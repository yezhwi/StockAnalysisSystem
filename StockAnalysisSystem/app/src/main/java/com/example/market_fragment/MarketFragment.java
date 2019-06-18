package com.example.market_fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.market_fragment.Bean.IndexBean;
import com.example.stockanalysissystem.R;
import com.example.util.HttpForIndex;
import com.example.util.StockAPI;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View mView;
    private Context nowcontext;
    private LinearLayout hg_stock_ly,sg_stock_ly,hk_stock_ly,us_stock_ly;
    private TextView shNowPrice,szNowPrice,shPriceChange,szPriceChange,shChangePercent,szChangePercent;
    private LinearLayout shIndex_layout,szIndex_layout;
    private static Intent intent4,intent5;
    public  static List<Object> obBean;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    IndexBean shindexBean = (IndexBean) msg.obj;
                    intent4=new Intent(nowcontext,IndexInfo.class);
                    intent4.putExtra("stock",shindexBean);
                    shPriceChange.setText(shindexBean.getChangePrice());
                    shNowPrice.setText(shindexBean.getNowPrice());
                    shChangePercent.setText(shindexBean.getChangePercent()+"%");
                    break;
                case 2:
                    IndexBean szindexBean= (IndexBean) msg.obj;
                    intent5=new Intent(nowcontext,IndexInfo.class);
                    intent5.putExtra("stock",szindexBean);
                    szNowPrice.setText(szindexBean.getNowPrice());
                    szPriceChange.setText(szindexBean.getChangePrice());
                    szChangePercent.setText(szindexBean.getChangePercent()+"%");
                    break;
            }

        }
    };




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MarketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MarketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketFragment newInstance(String param1, String param2) {
        MarketFragment fragment = new MarketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        // 通过创建不同的Bean对象来接收返回的泸股和深股的大盘指数信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                IndexBean shIndexBean = HttpForIndex.getSHIndexForNetWork(nowcontext, StockAPI.getHS("sh000001"));
                Message msg=new Message();
                msg.obj= shIndexBean;
                msg.what=1;
                mHandler.sendMessage(msg);

                IndexBean szIndexBean= HttpForIndex.getSHIndexForNetWork(nowcontext, StockAPI.getHS("sz399001"));
                Message msg1 = new Message();
                msg1.what=2;
                msg1.obj= szIndexBean;
                mHandler.sendMessage(msg1);
            }
        }).start();
    }

    private void initView(){
        hg_stock_ly=(LinearLayout)mView.findViewById(R.id.hg_stock);
        sg_stock_ly=(LinearLayout)mView.findViewById(R.id.sg_stock);
        hk_stock_ly=(LinearLayout)mView.findViewById(R.id.hk_stock);
        us_stock_ly=(LinearLayout)mView.findViewById(R.id.us_stock);
        shIndex_layout=(LinearLayout)mView.findViewById(R.id.sh_index);
        szIndex_layout=(LinearLayout)mView.findViewById(R.id.sz_index);
        shNowPrice=(TextView)mView.findViewById(R.id.sh_index_nowPrice);
        szNowPrice=(TextView)mView.findViewById(R.id.sz_index_nowPrice);
        shChangePercent=(TextView)mView.findViewById(R.id.sh_index_pricePercent);
        szChangePercent=(TextView)mView.findViewById(R.id.sz_index_pricePercent);
        shPriceChange=(TextView)mView.findViewById(R.id.sh_index_priceChange);
        szPriceChange=(TextView)mView.findViewById(R.id.sz_index_priceChange);

    }
    private void initEvent(){
        hg_stock_ly.setOnClickListener(this);
        sg_stock_ly.setOnClickListener(this);
        hk_stock_ly.setOnClickListener(this);
        us_stock_ly.setOnClickListener(this);
        shIndex_layout.setOnClickListener(this);
        szIndex_layout.setOnClickListener(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_market, container, false);
        return mView;
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
        nowcontext=context;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hg_stock:
                Intent intent=new Intent(nowcontext, hgStockActivity.class);
                intent.putExtra("pageName","沪股列表");
                startActivity(intent);
                break;
            case R.id.sg_stock:
                Intent intent1=new Intent(nowcontext, sgStockActivity.class);
                intent1.putExtra("pageName","深股列表");
                startActivity(intent1);
                break;
            case R.id.hk_stock:
                Intent intent2=new Intent(nowcontext,hkStockActivity.class);
                intent2.putExtra("pageName","港股列表");
                startActivity(intent2);
                break;
            case R.id.us_stock:
                Intent intent3=new Intent(nowcontext,usStockActivity.class);
                intent3.putExtra("pageName","美股列表");
                startActivity(intent3);
                break;
            case R.id.sh_index:
                startActivity(intent4);
                break;
            case R.id.sz_index:
                startActivity(intent5);
                break;
        }
    }
}
