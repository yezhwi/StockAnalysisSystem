package com.example.home_fragment.green_car;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.stockanalysissystem.R;

public class greenCarStockShow extends AppCompatActivity implements View.OnClickListener {
    private Button charge_bar_but,electric_control_but,car_produce_but,charge_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_car_stock_show);

        charge_bar_but=(Button)findViewById(R.id.charge_bar_but);
        electric_control_but=(Button)findViewById(R.id.electric_control_but);
        car_produce_but=(Button)findViewById(R.id.car_produce_but);
        charge_but=(Button)findViewById(R.id.charge_but);

        charge_bar_but.setOnClickListener(this);
        electric_control_but.setOnClickListener(this);
        car_produce_but.setOnClickListener(this);
        charge_but.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent charge_bar_intent;
        switch (v.getId()){
            case R.id.charge_bar_but:
                charge_bar_intent = new Intent(getBaseContext(), charge_bar_show.class);
                charge_bar_intent.putExtra("id","1");
                charge_bar_intent.putExtra("name","充电桩");
                startActivity(charge_bar_intent);
                break;
            case R.id.electric_control_but:
                charge_bar_intent = new Intent(getBaseContext(), charge_bar_show.class);
                charge_bar_intent.putExtra("id","2");
                charge_bar_intent.putExtra("name","电机电控");
                startActivity(charge_bar_intent);
                break;
            case R.id.car_produce_but:
                charge_bar_intent = new Intent(getBaseContext(), charge_bar_show.class);
                charge_bar_intent.putExtra("id","3");
                charge_bar_intent.putExtra("name","车辆制造");
                startActivity(charge_bar_intent);
                break;
            case R.id.charge_but:
                charge_bar_intent = new Intent(getBaseContext(), charge_bar_show.class);
                charge_bar_intent.putExtra("id","4");
                charge_bar_intent.putExtra("name","动力电池");
                startActivity(charge_bar_intent);
                break;
            default:
                break;
        }
    }
}
