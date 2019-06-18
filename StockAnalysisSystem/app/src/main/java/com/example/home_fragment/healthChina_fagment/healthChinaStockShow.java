package com.example.home_fragment.healthChina_fagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stockanalysissystem.R;

public class healthChinaStockShow extends AppCompatActivity implements View.OnClickListener {
    private Button medical_invest_but,new_medical_but,medical_tools_but,medical_department_but,
            food_health_but,information_medical_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_china_stock_show);

        medical_invest_but=(Button)findViewById(R.id.medical_invest_but);
        new_medical_but=(Button)findViewById(R.id.new_medical_but);
        medical_tools_but=(Button)findViewById(R.id.medical_tools_but);
        medical_department_but=(Button)findViewById(R.id.medical_department_but);
        food_health_but=(Button)findViewById(R.id.food_health_but);
        information_medical_but=(Button)findViewById(R.id.information_medical_but);

        medical_invest_but.setOnClickListener(this);
        new_medical_but.setOnClickListener(this);
        medical_tools_but.setOnClickListener(this);
        medical_department_but.setOnClickListener(this);
        food_health_but.setOnClickListener(this);
        information_medical_but.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent medical_invest_intent;
        switch (v.getId()){
            case R.id.medical_invest_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",1);
                medical_invest_intent.putExtra("name","医疗投资");
                startActivity(medical_invest_intent);
                break;
            case R.id.new_medical_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",2);
                medical_invest_intent.putExtra("name","新型医疗");
                startActivity(medical_invest_intent);
                break;
            case R.id. medical_tools_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",3);
                medical_invest_intent.putExtra("name","医疗器械");
                startActivity(medical_invest_intent);
                break;
            case R.id.medical_department_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",4);
                medical_invest_intent.putExtra("name","医疗机构");
                startActivity(medical_invest_intent);
                break;
            case R.id.food_health_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",5);
                medical_invest_intent.putExtra("name","食品保健");
                startActivity(medical_invest_intent);
                break;
            case R.id.information_medical_but:
                medical_invest_intent = new Intent(getBaseContext(), medical_invest_show.class);
                medical_invest_intent.putExtra("id",6);
                medical_invest_intent.putExtra("name","信息化医疗");
                startActivity(medical_invest_intent);
                break;
        }
    }
}
