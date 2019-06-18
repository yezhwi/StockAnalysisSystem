package com.example.home_fragment.saveEnergy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.stockanalysissystem.R;

public class saveEnergyStockShow extends AppCompatActivity implements View.OnClickListener {
    private Button landscaping_but,environment_modification_but,sewage_treatment_but,waste_management_but,
                    atmospheric_but,environment_monitor_but,nuclear_radiation_control_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_energy_stock_show);

        landscaping_but=(Button)findViewById(R.id.landscaping_but);
        environment_modification_but=(Button)findViewById(R.id.environment_modification_but);
        sewage_treatment_but=(Button)findViewById(R.id.sewage_treatment_but);
        waste_management_but=(Button)findViewById(R.id.waste_management_but);
        atmospheric_but=(Button)findViewById(R.id.atmospheric_but);
        environment_monitor_but=(Button)findViewById(R.id.environment_monitor_but);
        nuclear_radiation_control_but=(Button)findViewById(R.id.nuclear_radiation_control_but);

        landscaping_but.setOnClickListener(this);
        environment_modification_but.setOnClickListener(this);
        sewage_treatment_but.setOnClickListener(this);
        waste_management_but.setOnClickListener(this);
        atmospheric_but.setOnClickListener(this);
        environment_monitor_but.setOnClickListener(this);
        nuclear_radiation_control_but.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent landscaping_intent;
        switch (v.getId()){
            case R.id.landscaping_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","1");
                landscaping_intent.putExtra("name","园林绿化");
                startActivity(landscaping_intent);
                break;
            case R.id.environment_modification_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","2");
                landscaping_intent.putExtra("name","环境修复");
                startActivity(landscaping_intent);
                break;
            case R.id.sewage_treatment_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","3");
                landscaping_intent.putExtra("name","污水处理");
                startActivity(landscaping_intent);
                break;
            case R.id.waste_management_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","4");
                landscaping_intent.putExtra("name","固废处理");
                startActivity(landscaping_intent);
                break;
            case R.id.atmospheric_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","5");
                landscaping_intent.putExtra("name","大气治理");
                startActivity(landscaping_intent);
                break;
            case R.id.environment_monitor_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","6");
                landscaping_intent.putExtra("name","环境监测");
                startActivity(landscaping_intent);
                break;
            case R.id.nuclear_radiation_control_but:
                landscaping_intent = new Intent(getBaseContext(), landscaping_show.class);
                landscaping_intent.putExtra("id","7");
                landscaping_intent.putExtra("name","核辐射防治");
                startActivity(landscaping_intent);
                break;
            default:
                break;
        }
    }
}
