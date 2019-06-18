package com.example.market_fragment;

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


public class BuyStock extends AppCompatActivity implements View.OnClickListener {

    private Stock stock;
    private TextView stock_name,stock_id,now_price,change_price,change_precent,open_price,high_price,low_price,close_price;
    private EditText buy_num;
    Button kline_search,but_search;
    public static tradeDB newtrade=new tradeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_stock);
        stock=getIntent().getParcelableExtra("stock");
        stock_name=(TextView)findViewById(R.id.stock_name);
        stock_id=(TextView)findViewById(R.id.stock_id);
        now_price=(TextView)findViewById(R.id.now_price);
        change_price=(TextView)findViewById(R.id.change_price);
        change_precent=(TextView)findViewById(R.id.change_precent);
        open_price=(TextView)findViewById(R.id.open_price);
        high_price=(TextView)findViewById(R.id.high_price);
        low_price=(TextView)findViewById(R.id.low_price);
        close_price=(TextView)findViewById(R.id.close_price);
        stock_name.setText(stock.getName());
        stock_id.setText(stock.getSymbol());
        now_price.setText(stock.getTrade());
        change_price.setText(stock.getPricechange());
        change_precent.setText(stock.getChangepercent()+"%");
        open_price.setText(stock.getOpen());
        high_price.setText(stock.getHigh());
        low_price.setText(stock.getLow());
        close_price.setText(stock.getClose());
        kline_search=(Button)findViewById(R.id.kline_search);
        but_search=(Button)findViewById(R.id.but_search);
        kline_search.setOnClickListener(this);
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
