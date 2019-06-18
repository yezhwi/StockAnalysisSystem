package com.example.home_fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kLine_fragment.myKline;
import com.example.market_fragment.Bean.Stock;
import com.example.market_fragment.db.tradeDB;
import com.example.stockanalysissystem.R;

import org.litepal.LitePal;

import java.util.List;


public class SearchInfo extends AppCompatActivity implements View.OnClickListener {

    private Stock stock;
    private TextView stock_name,stock_id,now_price,change_price,change_precent,high_price,low_price,open_price,close_price,now_date,now_time;
    private EditText buy_num;
    Button kline_search,but_search;
    public static tradeDB newtrade=new tradeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
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
        stock_id.setText(stock.getSymbol());
        now_price.setText(stock.getTrade());
        change_price.setText(stock.getPricechange());
        change_precent.setText(stock.getChangepercent()+"%");
        high_price.setText(stock.getHigh());
        low_price.setText(stock.getLow());
        open_price.setText(stock.getOpen());
        close_price.setText(stock.getClose());
        now_date.setText(stock.getDate());
        now_time.setText(stock.getTime());
        kline_search=(Button)findViewById(R.id.kline_search);
        kline_search.setOnClickListener(this);

        but_search=(Button)findViewById(R.id.but_search);
        but_search.setOnClickListener(this);
        buy_num=(EditText)findViewById(R.id.buy_num);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kline_search:
                Intent intent=new Intent(getBaseContext(), myKline.class);
                intent.putExtra("stock_name",stock.getName());
                intent.putExtra("stock_id",stock.getSymbol());
                startActivity(intent);
                break;
            case R.id.but_search:
                String num=buy_num.getText().toString();
                String gid=stock.getSymbol();
                List<tradeDB> trades= LitePal.where("gid=?",gid).find(tradeDB.class);
                if(!trades.isEmpty()){
                    Log.i("trade","not null");
                    tradeDB trade=trades.get(0);
                    int nowNum,lastNum,allSum;
                    double lastBuy,nowBuy,allBuy;
                    nowNum=(int)Double.parseDouble(num);
                    lastNum=(int)Double.parseDouble(trade.sum);
                    allSum=nowNum+lastNum;
                    trade.sum=String.valueOf(allSum);
                    lastBuy=Double.parseDouble(trade.buyPrice);
                    nowBuy=Double.parseDouble(stock.getTrade());
                    allBuy=(nowBuy*nowNum+lastBuy*lastNum)/(nowNum+lastNum);
                    //盈亏不变由于在显示的时候自动就算盈亏，所以只要设置成本价即可
                    trade.setLastPrice(String.format("%.3f",nowBuy));
                    trade.setBuyPrice(String.format("%.3f",allBuy));
                    trade.save();
                }else {
                    Log.i("trade","is null");
                    newtrade.setGid(stock.getSymbol());
                    newtrade.setName(stock.getName());
                    newtrade.setBuyPrice(stock.getTrade());
                    newtrade.setLastPrice(stock.getTrade());
                    newtrade.setLastSum("0.0");
                    newtrade.setSum(num);
                    newtrade.save();
                }
                Toast.makeText(getBaseContext(),"买入成功",Toast.LENGTH_SHORT).show();
                buy_num.setText("");
                break;
        }
    }
}
