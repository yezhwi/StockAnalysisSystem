package com.example.market_fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kLine_fragment.myKline;
import com.example.market_fragment.Bean.IndexBean;
import com.example.stockanalysissystem.R;


public class IndexInfo extends AppCompatActivity implements View.OnClickListener {

    private IndexBean stock;
    private TextView stock_name,stock_id,now_price,change_price,change_precent,high_price,low_price,open_price,close_price,now_date,now_time;
    Button kline_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_info);
        stock=getIntent().getParcelableExtra("stock");
        stock_name=(TextView)findViewById(R.id.stock_name);
        stock_id=(TextView)findViewById(R.id.stock_id);
        now_price=(TextView)findViewById(R.id.now_price);
        change_price=(TextView)findViewById(R.id.change_price);
        change_precent=(TextView)findViewById(R.id.change_precent);
        high_price=(TextView)findViewById(R.id.high_price);
        low_price=(TextView)findViewById(R.id.low_price);
        open_price=(TextView)findViewById(R.id.open_price);
        close_price=(TextView)findViewById(R.id.close_price);
        now_date=(TextView)findViewById(R.id.now_date);
        now_time=(TextView)findViewById(R.id.now_time);

        stock_name.setText(stock.getName());
        stock_id.setText(stock.getGid());
        now_price.setText(stock.getNowPrice());
        change_price.setText(stock.getChangePrice());
        change_precent.setText(stock.getChangePercent()+"%");
        high_price.setText(stock.getTodayMax());
        low_price.setText(stock.getTodayMin());
        open_price.setText(stock.getTodayStartPri());
        close_price.setText(stock.getYestodEndPri());
        now_date.setText(stock.getDate());
        now_time.setText(stock.getTime());
        kline_search=(Button)findViewById(R.id.kline_search);
        kline_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kline_search:
                Intent intent=new Intent(getBaseContext(), myKline.class);
                intent.putExtra("stock_name",stock.getName());
                intent.putExtra("stock_id",stock.getGid());
                startActivity(intent);
                break;
        }
    }
}
