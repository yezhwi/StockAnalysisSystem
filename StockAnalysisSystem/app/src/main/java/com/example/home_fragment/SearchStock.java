package com.example.home_fragment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.market_fragment.Bean.Stock;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.StockAPI;
import com.example.util.hk_HttpRequestForList;
import com.example.util.us_HttpRequestForList;


import java.util.concurrent.CountDownLatch;

public class SearchStock extends AppCompatActivity implements View.OnClickListener {
    EditText gid;
    Button but_search;
    public static Stock stock=new Stock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stock);
        gid=(EditText)findViewById(R.id.gid);
        but_search=(Button)findViewById(R.id.but_search);
        but_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_search:
                //查询
                search(gid.getText().toString());
                Intent intent=new Intent(getBaseContext(), SearchInfo.class);
                intent.putExtra("stock",stock);
                startActivity(intent);
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
//    数据库位置/data/data/com.example.stockanalysissystem/databases
//CREATE TABLE tradedb (id integer primary key autoincrement,buyprice text, gid text, name text, nowprice text, sum text);

