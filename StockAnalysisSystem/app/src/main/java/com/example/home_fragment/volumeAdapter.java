package com.example.home_fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.market_fragment.db.StockInfoDB;
import com.example.stockanalysissystem.R;

import java.util.List;


public class volumeAdapter extends BaseAdapter {
    private List<StockInfoDB> list;
    private Context context;
    public volumeAdapter(Context context, List<StockInfoDB> list){
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
            view=layoutInflater.inflate(R.layout.lowprice_item,null);
        }
        //2.获取view上的子控件对象
        TextView stockName=(TextView)view.findViewById(R.id.lp_stock_name);
        TextView stockSymbol=(TextView)view.findViewById(R.id.lp_stock_symbol);
        TextView nowPrice=(TextView)view.findViewById(R.id.lp_now_price);
        TextView priceChange=(TextView)view.findViewById(R.id.lp_pice_change);
        TextView changePercent=(TextView)view.findViewById(R.id.lp_change_precent);
        TextView volume=(TextView)view.findViewById(R.id.lp_stock_volume);
        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        StockInfoDB stock=list.get(position);
        //4.将数据设置给这些子控件做显示
        stockName.setText(stock.getName());
        stockSymbol.setText(stock.getGid());
        nowPrice.setText(String.valueOf(stock.getNow_Price()));
        // nowPrice.setTextColor(this.getResources().getColor(R.color.deepred));

        priceChange.setText(String.valueOf(stock.getPrice_change()));
        changePercent.setText(stock.getChange_percent()+"%");
        volume.setText(String.valueOf(stock.getVolume()));
        volume.setTextColor(Color.parseColor("#990101"));
        return view;
    }
}
