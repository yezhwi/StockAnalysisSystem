package com.example.home_fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.market_fragment.db.StockInfoDB;
import com.example.stockanalysissystem.R;

import org.litepal.LitePal;


import java.util.List;

public class downStock extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView downStock_listView;
    private Handler upStockhandler =new Handler(){
        public void handleMessage(Message msg){
            List<StockInfoDB> allStock=(List<StockInfoDB>)msg.obj;
            if(allStock!=null&&allStock.size()>0){
                upStockAdapter listAdapter=new upStockAdapter(getBaseContext(),allStock);
                downStock_listView.setAdapter(listAdapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_stock);
        downStock_listView =(ListView)findViewById(R.id.upStock_listView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求数据库数据
//                List<News> newsList = DataSupport.select("title", "content")
//                        .where("commentcount > ?", "0")
//                        .order("publishdate desc").limit(10).find(News.class);

                List<StockInfoDB> allStocks= LitePal.select()
                        .where("categoryID!=? and categoryID!=? and change_percent<=?","4","3","-10.0")
                        .order("change_percent asc")
                        .limit(100)
                        .find(StockInfoDB.class);
                //ArrayList<Stock> allStocks = HttpRequestForList.getAllStockList(getBaseContext(), StockAPI.getHGAll(np));
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allStocks;
                upStockhandler.sendMessage(msg);
            }
        }).start();
        //3.设置listview条目的点击事件
        downStock_listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上bean对象中url做跳转
//        Stock stock = (Stock) parent.getItemAtPosition(position);
//        String url = StockAPI.getHS(stock.getSymbol());
//        //跳转浏览器
//        Intent intent = new Intent(this, StockDetail.class);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.putExtra("url",url);
//        startActivity(intent);
    }

}
