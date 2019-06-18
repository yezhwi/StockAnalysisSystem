package com.example.trade_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.market_fragment.Bean.Stock;
import com.example.stockanalysissystem.R;
import com.example.market_fragment.db.tradeDB;
import com.example.util.HttpRequestForList;
import com.example.util.StockAPI;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;


import org.litepal.LitePal;


import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tradeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tradeFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //卖出，持仓两个layout与button
    LinearLayout sell_detail,sum_detail;
    Button sell_but,sum_but;
    //卖出界面相关按钮
    TextView sell_gid,sell_num;
            Button sellBt;
    //持仓界面相关按钮
//    TextView sum_money,now_change,stock_num,cc_sum;
    TextView sum_money,now_change,stock_num,cc_sum;
    //持仓列表
    private ListView cc_listView;
    private View mView;
    Context nowcontext;
    private tradeAdapter myadapter;
    private Bundle bundle;

    public static Stock stock=new Stock();
    private  List<tradeDB> tradeDBList;

//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//           myadapter=(tradeAdapter) msg.obj;
//           cc_listView.setAdapter(myadapter);
//        }
//    };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public tradeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("onItemClick","onItemClick");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tradeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tradeFragment newInstance(String param1, String param2) {
        tradeFragment fragment = new tradeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundle=savedInstanceState;
        initView();
        initEvent();
        tradeDBList= LitePal.findAll(tradeDB.class);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                tradeAdapter adapter=new tradeAdapter(nowcontext,tradeDBList,mView);
//                Message msg=Message.obtain();
//                msg.obj=adapter;
//                handler.sendMessage(msg);
//            }
//        }).start();
        myadapter=new tradeAdapter(nowcontext,tradeDBList,mView);
        cc_listView.setAdapter(myadapter);

        //设置listview条目的点击事件
        cc_listView.setOnItemClickListener(this);

    }
    public void initView(){
        sell_detail=(LinearLayout)mView.findViewById(R.id.sell_detail);
        sum_detail=(LinearLayout)mView.findViewById(R.id.sum_detail);
        sum_detail.setVisibility(View.GONE);
        sell_but=(Button)mView.findViewById(R.id.sell_but);
        sum_but=(Button)mView.findViewById(R.id.sum_but);
        sell_gid=(TextView)mView.findViewById(R.id.sell_gid);
        sell_num=(TextView)mView.findViewById(R.id.sell_num);
        sellBt=(Button)mView.findViewById(R.id.sellBt);
        //持仓界面相关按钮
        sum_money=(TextView)mView.findViewById(R.id.sum_money);
        now_change=(TextView)mView.findViewById(R.id.now_change);
        stock_num=(TextView)mView.findViewById(R.id.stock_num);
        cc_sum=(TextView)mView.findViewById(R.id.cc_sum);
        //持仓列表
        cc_listView=(ListView)mView.findViewById(R.id.cc_listView);
    }
    public void initEvent(){
        sell_but.setOnClickListener(this);
        sum_but.setOnClickListener(this);
        sellBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sell_but:
                sell_detail.setVisibility(View.VISIBLE);
                sum_detail.setVisibility(View.GONE);
               // List<tradeDB> tradeDBList= LitePal.findAll(tradeDB.class);

                break;
            case R.id.sum_but:
                //盈亏显示界面
                sell_detail.setVisibility(View.GONE);
                sum_detail.setVisibility(View.VISIBLE);
                sum_money.setText("0");
                now_change.setText("0");
                //暴力刷新
                //notifyDataSetChanged();
                tradeDBList= LitePal.findAll(tradeDB.class);
                myadapter=new tradeAdapter(nowcontext,tradeDBList,mView);
                cc_listView.setAdapter(myadapter);
                //myadapter.notifyDataSetChanged();
                //持仓界面计算代码
                stock_num.setText(String.valueOf(tradeDBList.size()));
                double cSum=0;
                for( int i = 0 ; i <tradeDBList.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                    //System.out.println(list.get(i));
                    tradeDB t=tradeDBList.get(i);
                    cSum+=Double.parseDouble(t.sum);
                }
                cc_sum.setText(String.valueOf(cSum));

                break;
            case R.id.sellBt:
                //卖出股票界面
                String symbol=sell_gid.getText().toString();
                String sellNum=sell_num.getText().toString();
                int sNum=Integer.parseInt(sellNum);
                List<tradeDB> trades= LitePal.where("gid=?",symbol).find(tradeDB.class);
                if(!trades.isEmpty()){
                    tradeDB t=trades.get(0);
                    search(t.getGid());
                    String nowNum=t.getSum();
                    int nNum=(int)Double.parseDouble(nowNum);
                    if(sNum>nNum){
                        Toast.makeText(getContext(),"数量不足",Toast.LENGTH_SHORT).show();
                    }else if(sNum==nNum){
                        t.setSum("0");
                        t.save();
                        t.delete();
                    }else{
                        int allSum=nNum-sNum;
                        t.setSum(String.valueOf(allSum));
                        double nowprice;
                        nowprice=Double.parseDouble(stock.getTrade())-(Double.parseDouble(t.getLastSum()))/allSum;
                        t.setBuyPrice(String.format("%.2f",nowprice));
                        t.setLastPrice(stock.getTrade());
                        //卖出，盈亏不变，因为数量变了所以成本价要变，上次计算盈亏的价格要更新
                        t.save();
                    }
                    sell_num.setText("");
                    //暴力刷新
                    tradeDBList= LitePal.findAll(tradeDB.class);
                    myadapter=new tradeAdapter(nowcontext,tradeDBList,mView);
                    cc_listView.setAdapter(myadapter);
                    // myadapter.notifyDataSetChanged();
                    //notifyDataSetChanged()不好用1
                    Toast.makeText(getContext(),"卖出成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"没买过该股票",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.trade_list, container, false);
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
