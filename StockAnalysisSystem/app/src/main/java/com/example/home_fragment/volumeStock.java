package com.example.home_fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.market_fragment.db.StockInfoDB;
import com.example.stockanalysissystem.R;

import org.litepal.LitePal;


import java.util.List;

public class volumeStock extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lowPricelistView;
    private Handler lowPricehandler =new Handler(){
        public void handleMessage(Message msg){
            List<StockInfoDB> allStock=(List<StockInfoDB>)msg.obj;
            if(allStock!=null&&allStock.size()>0){
               volumeAdapter listAdapter=new volumeAdapter(getBaseContext(),allStock);
                lowPricelistView.setAdapter(listAdapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("volume","oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_price_stock);
        lowPricelistView =(ListView)findViewById(R.id.lowprice_listView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求数据库数据

//                List<News> newsList = DataSupport.select("title", "content")
//                        .where("commentcount > ?", "0")
//                        .order("publishdate desc").limit(10).find(News.class);

                List<StockInfoDB> allStocks= LitePal.select()
                        .where("categoryID!=? and categoryID!=?","3","4")
                        .order("volume desc")
                        .limit(100)
                        .find(StockInfoDB.class);//"now_Price>?","0.2"   ,"volume>?","500"
                //ArrayList<Stock> allStocks = HttpRequestForList.getAllStockList(getBaseContext(), StockAPI.getHGAll(np));
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allStocks;
                lowPricehandler.sendMessage(msg);
            }
        }).start();
        Log.i("volume","11111111111111");
        //3.设置listview条目的点击事件
        lowPricelistView.setOnItemClickListener(this);
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
