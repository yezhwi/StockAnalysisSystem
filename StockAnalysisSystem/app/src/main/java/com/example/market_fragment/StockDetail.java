package com.example.market_fragment;

import android.content.Intent;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.stockanalysissystem.GlideApp;
import com.example.stockanalysissystem.R;
import com.example.util.HttpGetKLine;
import com.example.util.StockAPI;
import com.example.market_fragment.Bean.KLineData;

import java.util.concurrent.CountDownLatch;

public class StockDetail extends AppCompatActivity implements View.OnClickListener {
    String gid;
    KLineData kLineData=new KLineData();

    private ImageView kLineImg;
    private Button min_but,day_but,week_but,mouth_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
        kLineImg=(ImageView)findViewById(R.id.k_line_img);
        min_but=(Button)findViewById(R.id.min_but);
        day_but=(Button)findViewById(R.id.day_but);
        week_but=(Button)findViewById(R.id.week_but);
        mouth_but=(Button)findViewById(R.id.mouth_but);

        min_but.setOnClickListener(this);
        day_but.setOnClickListener(this);
        week_but.setOnClickListener(this);
        mouth_but.setOnClickListener(this);

        //获取图片url
        Intent intent = getIntent();
        gid = intent.getStringExtra("gid");
        String s = gid.substring(0, 2);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if (s.equalsIgnoreCase("sh") || s.equalsIgnoreCase("sz")) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        kLineData = HttpGetKLine.getKLineData(getBaseContext(), StockAPI.getHS(gid));
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(Character.isDigit(s.charAt(0))&&Character.isDigit(s.charAt(1))){
            Log.i("hkgid",gid);
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        kLineData=HttpGetKLine.getKLineData(getBaseContext(), StockAPI.getHK(gid));
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
                        kLineData=HttpGetKLine.getKLineData(getBaseContext(), StockAPI.getUSA(gid));
                        // Log.i("trade:",stock.trade);
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.min_but:
                Log.i("Minurl:",kLineData.getMinurl());

                GlideApp.with(getBaseContext()).asGif().load(Uri.parse(kLineData.getMinurl())).into(kLineImg);
                break;
            case R.id.day_but:
                Log.i("Dayurl:",kLineData.getDayurl());
                GlideApp.with(getBaseContext()).asGif().load(Uri.parse(kLineData.getDayurl())).into(kLineImg);
                break;
            case R.id.week_but:
                Log.i("Weekurl:",kLineData.getWeekurl());
                GlideApp.with(getBaseContext()).asGif().load(Uri.parse(kLineData.getWeekurl())).into(kLineImg);
                break;
            case R.id.mouth_but:
                Log.i("Monthurl:",kLineData.getMonthurl());
                GlideApp.with(getBaseContext()).asGif().load(Uri.parse(kLineData.getMonthurl())).into(kLineImg);
                break;
        }

    }

}
