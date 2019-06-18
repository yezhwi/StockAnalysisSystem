package com.example.home_fragment.xaNewArea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.stockanalysissystem.R;

public class xaNewAreaShow extends AppCompatActivity implements View.OnClickListener {
    private Button xa_wisdom_but,xa_energy_but,xa_room_but,xa_basic_but,xa_city_design_but,
            xa_green_but,hb_local_but,xa_ppp_but,xa_traffic_but,xa_finance_but,xa_town_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xa_new_area_show);

        xa_wisdom_but=(Button)findViewById(R.id.xa_wisdom_but);
        xa_energy_but=(Button)findViewById(R.id.xa_energy_but);
        xa_room_but=(Button)findViewById(R.id.xa_room_but);
        xa_basic_but=(Button)findViewById(R.id.xa_basic_but);
        xa_city_design_but=(Button)findViewById(R.id.xa_city_design_but);
        xa_green_but=(Button)findViewById(R.id.xa_green_but);
        hb_local_but=(Button)findViewById(R.id.hb_local_but);
        xa_ppp_but=(Button)findViewById(R.id.xa_ppp_but);
        xa_traffic_but=(Button)findViewById(R.id.xa_traffic_but);
        xa_finance_but=(Button)findViewById(R.id.xa_finance_but);
        xa_town_but=(Button)findViewById(R.id.xa_town_but);


        xa_wisdom_but.setOnClickListener(this);
        xa_energy_but.setOnClickListener(this);
        xa_room_but.setOnClickListener(this);
        xa_basic_but.setOnClickListener(this);
        xa_city_design_but.setOnClickListener(this);
        xa_green_but.setOnClickListener(this);
        hb_local_but.setOnClickListener(this);
        xa_ppp_but.setOnClickListener(this);
        xa_traffic_but.setOnClickListener(this);
        xa_finance_but.setOnClickListener(this);
        xa_room_but.setOnClickListener(this);
        xa_town_but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent xa_wisdom_intent;
        switch (v.getId()){
            case R.id.xa_wisdom_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","1");
                xa_wisdom_intent.putExtra("name","智慧雄安");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_energy_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","2");
                xa_wisdom_intent.putExtra("name","雄安能源");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_room_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","3");
                xa_wisdom_intent.putExtra("name","雄安地产");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_basic_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","4");
                xa_wisdom_intent.putExtra("name","雄安基建");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_city_design_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","5");
                xa_wisdom_intent.putExtra("name","雄安城市设计");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_green_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","6");
                xa_wisdom_intent.putExtra("name","雄安环保");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.hb_local_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","7");
                xa_wisdom_intent.putExtra("name","河北本地股");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_ppp_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","8");
                xa_wisdom_intent.putExtra("name","园林PPP");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_traffic_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","9");
                xa_wisdom_intent.putExtra("name","雄安交运");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_finance_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","10");
                xa_wisdom_intent.putExtra("name","雄安金融");
                startActivity(xa_wisdom_intent);
                break;
            case R.id.xa_town_but:
                xa_wisdom_intent = new Intent(getBaseContext(),xa_wisdom_show.class);
                xa_wisdom_intent.putExtra("id","11");
                xa_wisdom_intent.putExtra("name","雄安特色小镇");
                startActivity(xa_wisdom_intent);
                break;
            default:
                break;
        }
    }
}
