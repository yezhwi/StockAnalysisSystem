package com.example.stockanalysissystem;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home_fragment.HomeFragment;
import com.example.market_fragment.MarketFragment;
import com.example.trade_fragment.tradeFragment;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener,
    HomeFragment.OnFragmentInteractionListener,
    MarketFragment.OnFragmentInteractionListener,
    tradeFragment.OnFragmentInteractionListener{
    private LinearLayout tab_Home;
    private ImageButton img_Home;
    private LinearLayout tab_Hq;
    private ImageButton img_Hq;
    private LinearLayout tab_Trade;
    private ImageButton img_Trade;

    private TextView home_text,hq_text,trade_text;

    private ViewPager myViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment>mFragment=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    private void initView(){
        myViewPager=(ViewPager) findViewById(R.id.Viewpager);
        tab_Home=(LinearLayout)findViewById(R.id.id_tab_home);
        img_Home=(ImageButton)findViewById(R.id.id_tab_home_img);
        home_text=(TextView)findViewById(R.id.home_text);
        tab_Hq=(LinearLayout)findViewById(R.id.id_tab_hq);
        img_Hq=(ImageButton)findViewById(R.id.id_tab_hq_img);
        hq_text=(TextView)findViewById(R.id.hq_text);
        tab_Trade=(LinearLayout)findViewById(R.id.id_tab_trade);
        img_Trade=(ImageButton)findViewById(R.id.id_tab_trade_img);
        trade_text=(TextView)findViewById(R.id.trade_text);

        mFragment=new ArrayList<Fragment>();
        Fragment homeFragment=new HomeFragment();
        Fragment marketFragment=new MarketFragment();
        Fragment tradeFra=new tradeFragment();
        mFragment.add(homeFragment);
        mFragment.add(marketFragment);
        mFragment.add(tradeFra);
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragment.get(arg0);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

        };

        myViewPager.setAdapter(mAdapter);
    }
    private void initEvent(){
        tab_Home.setOnClickListener(this);
        tab_Hq.setOnClickListener(this);
        tab_Trade.setOnClickListener(this);
        myViewPager.setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int arg0) {
                        // TODO Auto-generated method stub
                        int currentItem=myViewPager.getCurrentItem();
                        switch(currentItem)
                        {
                            case 0:
                                resetImg();
                                home_text.setTextColor(Color.parseColor("#ff0000"));//选中的红色
                                img_Home.setImageResource(R.drawable.menu_home_select_img);
                                break;
                            case 1:
                                resetImg();
                                hq_text.setTextColor(Color.parseColor("#ff0000"));
                                img_Hq.setImageResource(R.drawable.menu_hangqing_select_img);
                                break;
                            case 2:
                                resetImg();
                                trade_text.setTextColor(Color.parseColor("#ff0000"));
                                img_Trade.setImageResource(R.drawable.menu_trade_select_img);
                                break;
                            default:
                                break;
                        }
                    }
                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                        // TODO Auto-generated method stub
                    }
                }

        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_home:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.id_tab_hq:
                myViewPager.setCurrentItem(1);
                break;
            case R.id.id_tab_trade:
                myViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }

    }
    private void resetImg(){
        img_Home.setImageResource(R.drawable.menu_home_normal_img);
        home_text.setTextColor(Color.parseColor("#d9d9d9"));
        img_Hq.setImageResource(R.drawable.menu_hangqing_normal_img);
        hq_text.setTextColor(Color.parseColor("#d9d9d9"));
        img_Trade.setImageResource(R.drawable.menu_trade_normal_img);
        trade_text.setTextColor(Color.parseColor("#d9d9d9"));
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
