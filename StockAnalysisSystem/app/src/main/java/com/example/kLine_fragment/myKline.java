package com.example.kLine_fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kLine_fragment.fragment.FullScreenActivity;
import com.example.kLine_fragment.fragment.KLineChartFragment;
import com.example.kLine_fragment.fragment.TimeLineChartFragment;

import com.example.stockanalysissystem.R;

import java.util.ArrayList;
import java.util.List;

public class myKline extends AppCompatActivity implements View.OnClickListener,
TimeLineChartFragment.OnFragmentInteractionListener,
        KLineChartFragment.OnFragmentInteractionListener{

    private Button minBtn,dayBtn,weekBtn, monthBtn,qpBtn;
    ViewPager viewPager;
    private List<Fragment> mFragment;
    private FragmentPagerAdapter mAdapter;

    private TextView stock_name,stock_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_kline);

        initView();
        initEvent();
        Intent intent=getIntent();
        stock_name.setText(intent.getStringExtra("stock_name"));
        stock_id.setText(intent.getStringExtra("stock_id"));

    }
    private void initView(){
        minBtn=(Button)findViewById(R.id.min_line);
        dayBtn=(Button)findViewById(R.id.day_line);
        weekBtn=(Button)findViewById(R.id.week_line);
        monthBtn =(Button)findViewById(R.id.month_line);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        stock_name=(TextView)findViewById(R.id.stock_name);
        stock_id=(TextView)findViewById(R.id.stock_id);
        qpBtn=(Button)findViewById(R.id.qp);

        mFragment=new ArrayList<Fragment>();
        TimeLineChartFragment minFragment=TimeLineChartFragment.newInstance(1,stock_id.getText().toString());
        KLineChartFragment dayFragment=KLineChartFragment.newInstance(1,stock_id.getText().toString()),
                            weekFragment=KLineChartFragment.newInstance(7,stock_id.getText().toString()),
                            monthFragment=KLineChartFragment.newInstance(30,stock_id.getText().toString());
        mFragment.add(minFragment);
        mFragment.add(dayFragment);
        mFragment.add(weekFragment);
        mFragment.add(monthFragment);
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
            }
        };
        //viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(mAdapter);

    }

    private void initEvent(){
        minBtn.setOnClickListener(this);
        dayBtn.setOnClickListener(this);
        weekBtn.setOnClickListener(this);
        monthBtn.setOnClickListener(this);
        qpBtn.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.min_line:
                viewPager.setCurrentItem(0);
                break;
            case R.id.day_line:
                viewPager.setCurrentItem(1);
                break;
            case R.id.week_line:
                viewPager.setCurrentItem(2);
                break;
            case R.id.month_line:
                viewPager.setCurrentItem(3);
                break;
            case R.id.qp:
                FullScreenActivity.launch(myKline.this, viewPager.getCurrentItem(),stock_name.getText().toString(),stock_id.getText().toString());
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
