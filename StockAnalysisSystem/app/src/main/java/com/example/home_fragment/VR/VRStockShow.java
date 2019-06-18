package com.example.home_fragment.VR;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.stockanalysissystem.R;

public class VRStockShow extends AppCompatActivity implements View.OnClickListener {
    private Button vr_but1,vr_but2,vr_but3,vr_but4,vr_but5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrstock_show);

        vr_but1=(Button)findViewById(R.id.vr_but1);
        vr_but2=(Button)findViewById(R.id.vr_but2);
        vr_but3=(Button)findViewById(R.id.vr_but3);
        vr_but4=(Button)findViewById(R.id.vr_but4);
        vr_but5=(Button)findViewById(R.id.vr_but5);

        vr_but1.setOnClickListener(this);
        vr_but2.setOnClickListener(this);
        vr_but3.setOnClickListener(this);
        vr_but4.setOnClickListener(this);
        vr_but5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent vr_but1_intent;
        switch (v.getId()){
            case R.id.vr_but1:
                vr_but1_intent = new Intent(getBaseContext(), vrButShow.class);
                vr_but1_intent.putExtra("id","1");
                vr_but1_intent.putExtra("name","软件或系统平台");
                startActivity(vr_but1_intent);
                break;
            case R.id.vr_but2:
                vr_but1_intent = new Intent(getBaseContext(), vrButShow.class);
                vr_but1_intent.putExtra("id","2");
                vr_but1_intent.putExtra("name","控股或战略合作");
                startActivity(vr_but1_intent);
                break;
            case R.id.vr_but3:
                vr_but1_intent = new Intent(getBaseContext(), vrButShow.class);
                vr_but1_intent.putExtra("id","3");
                vr_but1_intent.putExtra("name","硬件制造");
                startActivity(vr_but1_intent);
                break;
            case R.id.vr_but4:
                vr_but1_intent = new Intent(getBaseContext(), vrButShow.class);
                vr_but1_intent.putExtra("id","4");
                vr_but1_intent.putExtra("name","视听触展示等后台配套");
                startActivity(vr_but1_intent);
                break;
            case R.id.vr_but5:
                vr_but1_intent = new Intent(getBaseContext(), vrButShow.class);
                vr_but1_intent.putExtra("id","5");
                vr_but1_intent.putExtra("name","设备自营");
                startActivity(vr_but1_intent);
                break;

            default:
                break;
        }
    }
}
