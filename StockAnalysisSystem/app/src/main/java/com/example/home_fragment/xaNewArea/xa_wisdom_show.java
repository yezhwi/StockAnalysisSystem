package com.example.home_fragment.xaNewArea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home_fragment.SearchInfo;
import com.example.home_fragment.ThemeDB.xaNewAreaStock;
import com.example.market_fragment.Bean.Stock;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.StockAPI;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;

import org.litepal.LitePal;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class xa_wisdom_show extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView xa_new_area_listView;
    private TextView page_name,stock_num;
    private List<xaNewAreaStock> xaNewAreaStockList;
    private xaNewAreaAdapter adapter;
    public static Stock stock=new Stock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xa_wisdom_show);
        xa_new_area_listView=(ListView)findViewById(R.id.xa_new_area_listView);

        page_name=(TextView)findViewById(R.id.page_name);
        stock_num=(TextView)findViewById(R.id.stock_num);
        Intent intent=getIntent();
        String categoryname=intent.getStringExtra("name");
        String categoryid=intent.getStringExtra("id");
        page_name.setText(categoryname);
        xaNewAreaStockList= LitePal.select()
                .where("category=?",categoryid)
                .find(xaNewAreaStock.class);
        stock_num.setText(String.valueOf(xaNewAreaStockList.size()));
        adapter=new xaNewAreaAdapter(getBaseContext(),xaNewAreaStockList,categoryname);
        xa_new_area_listView.setAdapter(adapter);
        xa_new_area_listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        xaNewAreaStock xaNewAreaStock = (xaNewAreaStock)parent.getItemAtPosition(position);
        search(xaNewAreaStock.getGid());
        Intent intent = new Intent(this, SearchInfo.class);
        intent.putExtra("stock",stock);
        startActivity(intent);
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
                        stock= HttpRequestForList.getSomeStockList(getBaseContext(), StockAPI.getHS(gid1));
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(Character.isDigit(s.charAt(0))&&Character.isDigit(s.charAt(1))){
            //gid=gid.substring(2,gid.length());
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        stock= hk_HttpRequestForList.getSomeStockList(getBaseContext(), StockAPI.getHK(gid1));
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
                        stock= us_HttpRequestForList.getSomeStockList(getBaseContext(), StockAPI.getUSA(gid1));
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
