package com.example.home_fragment.yq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stockanalysissystem.R;

import java.util.List;

//yqStockAdapter
public class yqStockShow extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView yq_item;
    private String[] ctype;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yq_stock_show);
        yq_item = (ListView) findViewById(R.id.yq_item);
        ctype = new String[]{"国机系", "国家电投系", "南方电网", "中核系", "华电系", "大唐系"
                , "华能系", "兵装系", "中石油系", "国网系", "国电系", "中粮系", "中远海运系",
                "中国建材系", "中石化系", "中国铁路系", "国投系", "中科院系", "清华系", "中化系",
                "恒天系", "华润系", "五矿系", "招商局系", "中航工业系", "中交系", "联通系",
                "中国电科系", "航天科技系", "航发系", "中国节能系", "兵工系", "北大系", "中国化工系",
                "中信系", "东航系", "宝武钢铁系", "一汽系", "新兴际华系", "国药系", "中车系",
                "航天科工系", "中国电子系", "中船重工系", "哈电系", "中国诚通系", "中船系"
        };
        ArrayAdapter<String> yq_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ctype);
        yq_item.setAdapter(yq_adapter);
        yq_item.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上内容做跳转
        String s=(String)parent.getItemAtPosition(position);
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();

        Intent yq_intent=new Intent(getBaseContext(),yq_item_show.class);
        yq_intent.putExtra("category",s);
        yq_intent.putExtra("i",position);
        startActivity(yq_intent);

    }

}
