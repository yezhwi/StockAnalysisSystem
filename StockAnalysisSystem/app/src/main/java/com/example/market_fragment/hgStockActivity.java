package com.example.market_fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.market_fragment.Bean.Stock;
import com.example.stockanalysissystem.R;
import com.example.util.HttpRequestForList;
import com.example.util.StockAPI;

import java.util.ArrayList;

public class hgStockActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private ListView listView;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            ArrayList<Stock> allStock=(ArrayList<Stock>)msg.obj;
            if(allStock!=null&&allStock.size()>0){
                MarketAdapter listAdapter=new MarketAdapter(getBaseContext(),allStock);
                listView.setAdapter(listAdapter);
            }
        }
    };
    TextView now_page,sum_page,page_name;
    Button last_page,next_page;
    int np;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hg_stock_activity);
        listView=(ListView)findViewById(R.id.stock_listView);

        page_name=(TextView)findViewById(R.id.page_name);
        page_name.setText(getIntent().getStringExtra("pageName"));
        now_page=(TextView)findViewById(R.id.now_page);
        sum_page=(TextView)findViewById(R.id.sum_page);
        last_page=(Button)findViewById(R.id.last_page);
        next_page=(Button)findViewById(R.id.next_page);
        sum_page.setText("36");
        np=Integer.parseInt(now_page.getText().toString());
        Log.d("MainActivity,np:",""+np);
        if(np<=1){
            last_page.setEnabled(false);
        }else if(np>=Integer.parseInt(sum_page.getText().toString())){
            next_page.setEnabled(false);
        }else {
            last_page.setEnabled(true);
            next_page.setEnabled(true);
        }
        last_page.setOnClickListener(this);
        next_page.setOnClickListener(this);
        //2.通过网络获取服务器上的股票数据用list封装 ,获取网络数据需要在子线程中做
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络数据
                ArrayList<Stock> allStocks = HttpRequestForList.getAllStockList(getBaseContext(), StockAPI.getHGAll(1));
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allStocks;
                handler.sendMessage(msg);
            }
        }).start();
        //3.设置listview条目的点击事件
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上bean对象中url做跳转
        Stock stock = (Stock)parent.getItemAtPosition(position);
        Intent intent = new Intent(this,BuyStock.class);
        intent.putExtra("stock",stock);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.last_page:
                np--;
                break;
            case R.id.next_page:
                np++;
                break;
            default:
                break;
        }
        now_page.setText(Integer.toString(np));
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络数据
                ArrayList<Stock> allStocks = HttpRequestForList.getAllStockList(getBaseContext(), StockAPI.getHGAll(np));
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allStocks;
                handler.sendMessage(msg);
            }
        }).start();
        if(np<=1){
            last_page.setEnabled(false);
        }else if(np>=Integer.parseInt(sum_page.getText().toString())){
            next_page.setEnabled(false);
        }else {
            last_page.setEnabled(true);
            next_page.setEnabled(true);
        }
    }
}
