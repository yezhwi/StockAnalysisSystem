package com.example.home_fragment.FiveG;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.stockanalysissystem.R;

public class FiveGStockShow extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //    private Button charge_bar_but,electric_control_but,car_produce_but,charge_but;
    private ListView fg_item;
    private String[] ctype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_gstock_show);

        fg_item = (ListView) findViewById(R.id.fg_item);
        ctype = new String[]{
                "基带芯片","大数据","云计算","射频器件","光通信","芯片"
                ,"基站天线","通讯传输设备","通信网络建设","终端","智能安防", "运营商",
                "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
                "无人机"
        };
        ArrayAdapter<String> yq_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ctype);
        fg_item.setAdapter(yq_adapter);
        fg_item.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上内容做跳转
        String s=(String)parent.getItemAtPosition(position);
       // Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
        Intent fg_intent=new Intent(getBaseContext(),FiveGItemShow.class);
        fg_intent.putExtra("category",s);
        fg_intent.putExtra("i",position);
        startActivity(fg_intent);
    }
}
