package com.example.trade_fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.market_fragment.Bean.Stock;
import com.example.market_fragment.db.tradeDB;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;
import com.example.util.StockAPI;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class tradeAdapter extends BaseAdapter {
    static Stock stock=new Stock();
    double nowp;
    String gid;
    private List<tradeDB>list;
    private Context context;
    private LayoutInflater inflater;
    private View fview;
    public tradeAdapter(Context context,List<tradeDB> list1,View view){
        this.context=context;
        this.list=list1;
        this.inflater=LayoutInflater.from(context);
        this.fview=view;
    }

    @Override
    public int getCount() {
        Log.i("count",""+list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View view=null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
        //LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //将一个布局文件转换成一个view对象
        //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)
        //包一层作为codify的返回值, 一般传null
        View view=inflater.inflate(R.layout.tradedb_list_layout,null);
        tradeDB trade=list.get(position);

        //2.获取view上的子控件对象
        TextView trade_stock_name=(TextView)view.findViewById(R.id.trade_stock_name);
        TextView trade_stock_symbol=(TextView)view.findViewById(R.id.trade_stock_symbol);
        TextView trade_sum=(TextView)view.findViewById(R.id.trade_sum);
        TextView buy_price=(TextView)view.findViewById(R.id.buy_price);
        TextView now_price=(TextView)view.findViewById(R.id.now_price);
        TextView trade_changeSum=(TextView)view.findViewById(R.id.trade_changeSum);


        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        //tradeDB trade=list.get(position);
        gid=trade.getGid();
        String s=gid.substring(0,2);
        Log.i("gid",gid);
        Log.i("s",s);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if(s.equalsIgnoreCase("sh")||s.equalsIgnoreCase("sz")){
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= HttpRequestForList.getSomeStockList(context, StockAPI.getHS(gid));
                        nowp=Double.parseDouble(stock.trade);//stock.getTrade()
                        // Log.i("trade:",stock.trade);
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(s.equalsIgnoreCase("hk")){
            gid=gid.substring(2,gid.length());
            Log.i("hkgid",gid);
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= hk_HttpRequestForList.getSomeStockList(context, StockAPI.getHK(gid));
                        nowp=Double.parseDouble(stock.trade);//stock.getTrade()
                        // Log.i("trade:",stock.trade);
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Log.i("usgid",gid);
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= us_HttpRequestForList.getSomeStockList(context, StockAPI.getUSA(gid));
                        nowp=Double.parseDouble(stock.trade);//stock.getTrade()
                        // Log.i("trade:",stock.trade);
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        //4.将数据设置给这些子控件做显示
        trade_stock_name.setText(trade.getName());
        trade_stock_symbol.setText(trade.getGid());
        trade_sum.setText(trade.getSum());
        buy_price.setText(trade.getBuyPrice());
        now_price.setText(stock.getTrade());

        int tradesum=(int)Double.parseDouble(trade.getSum());
        double lastP=Double.parseDouble(trade.getLastPrice());
        double sum=(nowp-lastP)*tradesum;
        Log.i("lastP",","+lastP);
        Log.i("sum1",","+sum);
        sum+=Double.parseDouble(trade.getLastSum());
        Log.i("trade.getLastSum",","+trade.getLastSum());

        Log.i("sum",String.format("%.3f",sum));
        //现在的盈亏=上次盈亏+（根据现在的价格-上次计算盈亏时的价格）*数量
        trade.setLastSum(String.format("%.3f",sum));
        trade.setLastPrice(stock.trade);
        trade.save();
        trade_changeSum.setText(String.format("%.3f",sum));

        //持仓的计算
        //持仓界面计算
        TextView sum_money,now_change;
        double sMoney,sChange;//总市值，总盈亏
        sum_money=(TextView)fview.findViewById(R.id.sum_money);
        now_change=(TextView)fview.findViewById(R.id.now_change);
        Log.i("sum_money",sum_money.getText().toString());
        try {
            sMoney=Double.parseDouble(sum_money.getText().toString())+nowp*Double.parseDouble(trade.getSum());
            sum_money.setText(String.format("%.3f",sMoney));
            sChange=Double.parseDouble(now_change.getText().toString());
            sChange+=sum;
            now_change.setText(String.format("%.3f",sChange));
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.i("Text:",trade_stock_name.getText().toString()+","+trade_stock_symbol.getText().toString()+","+trade_sum.getText().toString()
                +","+buy_price.getText().toString()+","+now_price.getText().toString()+","+trade_changeSum.getText());
        return view;
    }
}
