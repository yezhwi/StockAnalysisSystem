package com.example.home_fragment.xaNewArea;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.market_fragment.Bean.Stock;
import com.example.home_fragment.ThemeDB.xaNewAreaStock;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;
import com.example.util.StockAPI;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class xaNewAreaAdapter extends BaseAdapter {
    static Stock stock=new Stock();

    String gid;
    private List<xaNewAreaStock>list;
    private Context context;
    private LayoutInflater inflater;
    private String categoryID;
    public xaNewAreaAdapter(Context context, List<xaNewAreaStock> list1, String category){
        this.context=context;
        this.list=list1;
        this.inflater=LayoutInflater.from(context);
        this.categoryID=category;

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
        View view=null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if(convertView!=null){
            view=convertView;
            Log.i("convertView","不为空");
        }else {
            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
            //LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //将一个布局文件转换成一个view对象
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)
            //包一层作为codify的返回值, 一般传null
            view=inflater.inflate(R.layout.health_china_list_layout,null);
            Log.i("convertView","为空");
        }
        //View view=inflater.inflate(R.layout.health_china_list_layout,null);
        xaNewAreaStock xaStock=list.get(position);

        //2.获取view上的子控件对象
        TextView xa_stock_name=(TextView)view.findViewById(R.id.health_china_stock_name);
        TextView xa_stock_symbol=(TextView)view.findViewById(R.id.health_china_stock_symbol);
        TextView xa_now_price=(TextView)view.findViewById(R.id.health_china_now_price);
        TextView xa_change_price=(TextView)view.findViewById(R.id.health_china_change_price);
        TextView xa_change_percent=(TextView)view.findViewById(R.id.health_china_change_percent);
        TextView xa_theme_name=(TextView)view.findViewById(R.id.health_china_theme_name);


        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        //tradeDB trade=list.get(position);
        gid=xaStock.getGid();
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
                        //nowp=Double.parseDouble(stock.trade);//stock.getTrade()
                        // Log.i("trade:",stock.trade);
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(Character.isDigit(s.charAt(0))&&Character.isDigit(s.charAt(1))){
            gid=gid.substring(2,gid.length());
            Log.i("hkgid",gid);
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= hk_HttpRequestForList.getSomeStockList(context, StockAPI.getHK(gid));
                        // nowp=Double.parseDouble(stock.trade);//stock.getTrade()
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
                        //nowp=Double.parseDouble(stock.trade);//stock.getTrade()
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
        xa_stock_name.setText(xaStock.getName());
        xa_stock_symbol.setText(xaStock.getGid());
        xa_now_price.setText(stock.getTrade());
        xa_change_price.setText(stock.getPricechange());
        xa_change_percent.setText(stock.getChangepercent());
        xa_theme_name.setText(categoryID);

        return view;
    }
}
