package com.example.market_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.market_fragment.Bean.Stock;
import com.example.stockanalysissystem.R;

import java.util.ArrayList;

public class MarketAdapter extends BaseAdapter {
    private ArrayList<Stock>list;
    //private Stock stock;
    private Context context;
    public MarketAdapter(Context context,ArrayList<Stock> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
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
        }else {
            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //将一个布局文件转换成一个view对象
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)
            //包一层作为codify的返回值, 一般传null
            view=layoutInflater.inflate(R.layout.stock_item_layout,null);
        }
        //2.获取view上的子控件对象
        TextView stockName=(TextView)view.findViewById(R.id.stock_name);
        TextView stockSymbol=(TextView)view.findViewById(R.id.stock_symbol);
        TextView trade=(TextView)view.findViewById(R.id.trade);
        TextView priceChange=(TextView)view.findViewById(R.id.pricechange);
        TextView changePercent=(TextView)view.findViewById(R.id.changeprecent);
        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        Stock stockbean=list.get(position);
        //4.将数据设置给这些子控件做显示
        stockName.setText(stockbean.getName());
        stockSymbol.setText(stockbean.getSymbol());
        trade.setText(stockbean.getTrade());
        priceChange.setText(stockbean.getPricechange());
        changePercent.setText(stockbean.getChangepercent());
        return view;
    }
}
