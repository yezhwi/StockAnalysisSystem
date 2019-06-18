package com.example.home_fragment;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.home_fragment.OneRoad.OneRoadStockShow;
import com.example.home_fragment.healthChina_fagment.healthChinaStockShow;
import com.example.market_fragment.Bean.Stock;
import com.example.market_fragment.db.StockInfoDB;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;
import com.example.util.StockAPI;
import com.example.home_fragment.green_car.greenCarStockShow;
import com.example.home_fragment.xaNewArea.xaNewAreaShow;
import com.example.home_fragment.saveEnergy.saveEnergyStockShow;
import com.example.home_fragment.VR.VRStockShow;
import com.example.home_fragment.yq.yqStockShow;
import com.example.home_fragment.AI.AIStockShow;
import com.example.home_fragment.FiveG.FiveGStockShow;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button stock_search_but,upstock_but,downstock_but,update_but,lowPrice_but,volume_but, theme_but,more_but;
    View mView;
    //题材选股里的功能
    private Button health_china_but,green_car_but,xa_newArea_but,jn_but, VR_but,One_Road_but,yq_but,AI_but,FG_but,Add_but;
    LinearLayout lowLayout;
    private Context nowcontext;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static ArrayList<StockInfoDB> allStocks=new ArrayList<>();
    public static StockInfoDB stockIDB=new StockInfoDB();
    private OnFragmentInteractionListener mListener;
    public static Stock stock=new Stock();
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        Connector.getDatabase();//用litepal创建数据库只需这一条语句

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.home_title, container, false);
        return mView;
    }

    public void initView(){

        stock_search_but =(Button)mView.findViewById(R.id.but_1);
        upstock_but=(Button)mView.findViewById(R.id.but_2);
        downstock_but=(Button)mView.findViewById(R.id.but_3);
        update_but=(Button)mView.findViewById(R.id.but_4);
        lowPrice_but=(Button)mView.findViewById(R.id.but_5);
        volume_but=(Button)mView.findViewById(R.id.but_6);
        theme_but =(Button)mView.findViewById(R.id.but_7);
        more_but=(Button)mView.findViewById(R.id.but_8);
        lowLayout=(LinearLayout)mView.findViewById(R.id.low);
        lowLayout.setVisibility(View.GONE);

        Add_but=(Button)mView.findViewById(R.id.Add_but);
        //专题选股子按钮
        health_china_but=(Button)mView.findViewById(R.id.health_china_but);
        green_car_but=(Button)mView.findViewById(R.id.green_car_but);
        xa_newArea_but=(Button)mView.findViewById(R.id.xa_newArea_but);
        jn_but=(Button)mView.findViewById(R.id.jn_but);//jn_but节能环保
        VR_but =(Button)mView.findViewById(R.id.VR_but);
        One_Road_but=(Button)mView.findViewById(R.id.One_Road_but);
        yq_but=(Button)mView.findViewById(R.id.yq_but);//央企混改
        AI_but=(Button)mView.findViewById(R.id.AI_but);
        FG_but=(Button)mView.findViewById(R.id.FG_but);
    }
    public void initEvent(){

        stock_search_but.setOnClickListener(this);
        upstock_but.setOnClickListener(this);
        downstock_but.setOnClickListener(this);

        more_but.setOnClickListener(this);

        update_but.setOnClickListener(this);
        lowPrice_but.setOnClickListener(this);
        volume_but.setOnClickListener(this);
        theme_but.setOnClickListener(this);
        Add_but.setOnClickListener(this);
        health_china_but.setOnClickListener(this);
        green_car_but.setOnClickListener(this);
        xa_newArea_but.setOnClickListener(this);
        jn_but.setOnClickListener(this);
        VR_but.setOnClickListener(this);
        One_Road_but.setOnClickListener(this);
        yq_but.setOnClickListener(this);
        AI_but.setOnClickListener(this);
        FG_but.setOnClickListener(this);
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
            case R.id.but_1:
                //股市个股查询
                Intent intent7 = new Intent(nowcontext, SearchStock.class);
                startActivity(intent7);
                break;
            case R.id.but_2:
                //涨幅榜
                Intent upStock_intent = new Intent(nowcontext, upStock.class);
                startActivity(upStock_intent);
                break;
            case R.id.but_3:
                //跌幅榜
                Intent downStock_intent = new Intent(nowcontext, downStock.class);
                startActivity(downStock_intent);
                break;
            case R.id.but_4:
                //最坑人的本地数据库更新
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        LitePal.deleteAll(StockInfoDB.class);
                        List<StockInfoDB> dbList= LitePal.findAll(StockInfoDB.class);
                        Log.i("sum",""+dbList.size());
                        //更新沪股数据
                        for(int page=1;page<=36;page++){
                            allStocks.clear();
                            allStocks = HttpRequestForList.getUpdateStockList(nowcontext, StockAPI.getHGAll(page));
                            for(int i=0;i<allStocks.size();i++){
                                stockIDB=allStocks.get(i);
                                stockIDB.categoryID=1;
                                stockIDB.save();
                            }
                        }

                        //更新深股数据
                        for(int page=1;page<=55;page++){
                            allStocks.clear();
                            allStocks = HttpRequestForList.getUpdateStockList(nowcontext, StockAPI.getSGAll(page));
                            for(int i=0;i<allStocks.size();i++){
                                stockIDB=allStocks.get(i);
                                stockIDB.categoryID=2;
                                stockIDB.save();
                            }
                        }

                        //更新港股数据
                        for(int page=1;page<=9;page++){
                            allStocks.clear();
                            allStocks = hk_HttpRequestForList.getUpdateStockList(nowcontext, StockAPI.getHKAll(page));
                            for(int i=0;i<allStocks.size();i++){
                                stockIDB=allStocks.get(i);
                                stockIDB.categoryID=3;
                                stockIDB.save();
                            }
                        }

                        //更新美股数据
                        for(int page=1;page<=234;page++){
                            allStocks.clear();
                            allStocks = us_HttpRequestForList.getUpdateStockList(nowcontext, StockAPI.getUSAAll(page));
                            for(int i=0;i<allStocks.size();i++){
                                stockIDB=allStocks.get(i);
                                stockIDB.categoryID=4;
                                stockIDB.save();
                            }
                        }

                        dbList= LitePal.findAll(StockInfoDB.class);
                        Log.i("sum",""+dbList.size());//sum=13318共用时四分钟
                    }
                }).start();
                Toast.makeText(nowcontext,"更新完成",Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_5:
                //低价选股
                Intent intent10 = new Intent(nowcontext, lowPriceStock.class);
                startActivity(intent10);
                break;
            case R.id.but_6:
                //成交量选股
                Intent intent11 = new Intent(nowcontext, volumeStock.class);
                startActivity(intent11);
                break;
            case R.id.but_7:
                //题材选股
                if(lowLayout.getVisibility()==View.GONE){
                    lowLayout.setVisibility(View.VISIBLE);
                }else {
                    lowLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.but_8:
                Log.i("s","万能的Button。");
                /*
                //更多功能
                List<tradeDB>tradeDBList= LitePal.findAll(tradeDB.class);
                Log.i("size:",""+tradeDBList.size());
                for( int i = 0 ; i <tradeDBList.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                    tradeDB t=tradeDBList.get(i);
                    search(t.getGid());
                    double sum=(Double.parseDouble(stock.getTrade())-Double.parseDouble(t.getBuyPrice()))*Double.parseDouble(t.getSum());
                    t.setLastSum(String.format("%.3f",sum));
                    t.setLastPrice(stock.getTrade());
                    t.save();
                    Log.i("ReallyPrice:",""+t.getLastPrice());
                }*/
                break;
            case R.id.Add_but:
                //题材中的数据添加
                Intent intent12 = new Intent(nowcontext, AddStockToTheme.class);
                startActivity(intent12);
                break;

            //题材选股子控件
            case R.id.health_china_but:
                //健康中国
                Intent intent13 = new Intent(nowcontext, healthChinaStockShow.class);
                startActivity(intent13);
                break;
            case R.id.green_car_but:
                //新能源汽车
                Intent intent14 = new Intent(nowcontext, greenCarStockShow.class);
                startActivity(intent14);
                break;
            case R.id.xa_newArea_but:
                //雄安新区
                Intent intent15 = new Intent(nowcontext, xaNewAreaShow.class);
                startActivity(intent15);
                break;
            case R.id.jn_but:
                //节能环保jn_but
                Intent intent16 = new Intent(nowcontext, saveEnergyStockShow.class);
                startActivity(intent16);
                break;
            case R.id.VR_but:
                Intent intent17 = new Intent(nowcontext, VRStockShow.class);
                startActivity(intent17);
                break;
            case R.id.One_Road_but:
                Intent intent18 = new Intent(nowcontext, OneRoadStockShow.class);
                startActivity(intent18);
                break;
            case R.id.yq_but:
                Intent intent19 = new Intent(nowcontext, yqStockShow.class);
                startActivity(intent19);
                break;
            case R.id.AI_but:
                Intent intent20 = new Intent(nowcontext, AIStockShow.class);
                startActivity(intent20);
                break;
            case R.id.FG_but:
                Intent intent21 = new Intent(nowcontext, FiveGStockShow.class);
                startActivity(intent21);
                break;
            default:
                break;
        }
    }
    private void search(final String gid1){
        String s=gid1.substring(0,2);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if(s.equalsIgnoreCase("sh")||s.equalsIgnoreCase("sz")){
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= HttpRequestForList.getSomeStockList(nowcontext, StockAPI.getHS(gid1));
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(s.equalsIgnoreCase("hk")){
            final String t=gid1.substring(2,gid1.length());
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= hk_HttpRequestForList.getSomeStockList(nowcontext, StockAPI.getHK(t));
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= us_HttpRequestForList.getSomeStockList(nowcontext, StockAPI.getUSA(gid1));
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
