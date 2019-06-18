package com.example.home_fragment.OneRoad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.stockanalysissystem.R;

public class OneRoadStockShow extends AppCompatActivity implements View.OnClickListener {
    private Button one_road_but1,one_road_but2,one_road_but3,one_road_but4,one_road_but5,one_road_but6,one_road_but7,one_road_but8,
            one_road_but9,one_road_but10,one_road_but11,one_road_but12,one_road_but13,one_road_but14,one_road_but15,one_road_but16,
            one_road_but17,one_road_but18,one_road_but19,one_road_but20,one_road_but21,one_road_but22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_road_stock_show);

        one_road_but1=(Button)findViewById(R.id.one_road_but1);
        one_road_but2=(Button)findViewById(R.id.one_road_but2);
        one_road_but3=(Button)findViewById(R.id.one_road_but3);
        one_road_but4=(Button)findViewById(R.id.one_road_but4);
        one_road_but5=(Button)findViewById(R.id.one_road_but5);
        one_road_but6=(Button)findViewById(R.id.one_road_but6);
        one_road_but7=(Button)findViewById(R.id.one_road_but7);
        one_road_but8=(Button)findViewById(R.id.one_road_but8);
        one_road_but9=(Button)findViewById(R.id.one_road_but9);
        one_road_but10=(Button)findViewById(R.id.one_road_but10);
        one_road_but11=(Button)findViewById(R.id.one_road_but11);
        one_road_but12=(Button)findViewById(R.id.one_road_but12);
        one_road_but13=(Button)findViewById(R.id.one_road_but13);
        one_road_but14=(Button)findViewById(R.id.one_road_but14);
        one_road_but15=(Button)findViewById(R.id.one_road_but15);
        one_road_but16=(Button)findViewById(R.id.one_road_but16);
        one_road_but17=(Button)findViewById(R.id.one_road_but17);
        one_road_but18=(Button)findViewById(R.id.one_road_but18);
        one_road_but19=(Button)findViewById(R.id.one_road_but19);
        one_road_but20=(Button)findViewById(R.id.one_road_but20);
        one_road_but21=(Button)findViewById(R.id.one_road_but21);
        one_road_but22=(Button)findViewById(R.id.one_road_but22);

        one_road_but1.setOnClickListener(this);
        one_road_but2.setOnClickListener(this);
        one_road_but3.setOnClickListener(this);
        one_road_but4.setOnClickListener(this);
        one_road_but5.setOnClickListener(this);
        one_road_but6.setOnClickListener(this);
        one_road_but7.setOnClickListener(this);
        one_road_but8.setOnClickListener(this);
        one_road_but9.setOnClickListener(this);
        one_road_but10.setOnClickListener(this);
        one_road_but11.setOnClickListener(this);
        one_road_but12.setOnClickListener(this);
        one_road_but13.setOnClickListener(this);
        one_road_but14.setOnClickListener(this);
        one_road_but15.setOnClickListener(this);
        one_road_but16.setOnClickListener(this);
        one_road_but17.setOnClickListener(this);
        one_road_but18.setOnClickListener(this);
        one_road_but19.setOnClickListener(this);
        one_road_but20.setOnClickListener(this);
        one_road_but21.setOnClickListener(this);
        one_road_but22.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent one_road_but_intent;
        switch (v.getId()){
            case R.id.one_road_but1:
                one_road_but_intent= new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but1.getText().toString());
                one_road_but_intent.putExtra("id",1);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but2:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but2.getText().toString());
                one_road_but_intent.putExtra("id",2);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but3:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but3.getText().toString());
                one_road_but_intent.putExtra("id",3);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but4:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but4.getText().toString());
                one_road_but_intent.putExtra("id",4);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but5:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but5.getText().toString());
                one_road_but_intent.putExtra("id",5);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but6:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but6.getText().toString());
                one_road_but_intent.putExtra("id",6);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but7:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but7.getText().toString());
                one_road_but_intent.putExtra("id",7);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but8:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but8.getText().toString());
                one_road_but_intent.putExtra("id",8);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but9:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but9.getText().toString());
                one_road_but_intent.putExtra("id",9);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but10:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but10.getText().toString());
                one_road_but_intent.putExtra("id",10);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but11:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but11.getText().toString());
                one_road_but_intent.putExtra("id",11);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but12:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but12.getText().toString());
                one_road_but_intent.putExtra("id",12);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but13:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but13.getText().toString());
                one_road_but_intent.putExtra("id",13);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but14:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but14.getText().toString());
                one_road_but_intent.putExtra("id",14);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but15:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but15.getText().toString());
                one_road_but_intent.putExtra("id",15);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but16:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but16.getText().toString());
                one_road_but_intent.putExtra("id",16);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but17:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but17.getText().toString());
                one_road_but_intent.putExtra("id",17);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but18:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but18.getText().toString());
                one_road_but_intent.putExtra("id",18);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but19:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but19.getText().toString());
                one_road_but_intent.putExtra("id",19);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but20:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but20.getText().toString());
                one_road_but_intent.putExtra("id",20);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but21:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but21.getText().toString());
                one_road_but_intent.putExtra("id",21);
                startActivity(one_road_but_intent);
                break;
            case R.id.one_road_but22:
                one_road_but_intent = new Intent(getBaseContext(), one_road_but_Show.class);
                one_road_but_intent.putExtra("category",one_road_but22.getText().toString());
                one_road_but_intent.putExtra("id",22);
                startActivity(one_road_but_intent);
                break;
            default:
                break;
        }
    }
}
