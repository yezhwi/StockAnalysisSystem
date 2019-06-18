package com.example.kLine_fragment.fragment;

import android.content.Context;
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


import com.example.stockanalysissystem.R;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity implements View.OnClickListener,
        TimeLineChartFragment.OnFragmentInteractionListener,
        KLineChartFragment.OnFragmentInteractionListener {

    private Button minBtn,dayBtn,weekBtn, monthBtn;
    private TextView stock_name,stock_id;
    ViewPager viewPager;
    private List<Fragment> mFragment;
    private FragmentPagerAdapter mAdapter;


    public static void launch(Context context, int index,String name,String id) {
        Intent intent = new Intent(context, FullScreenActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        initView();
        initEvent();
    }
    private void initView(){
        minBtn=(Button)findViewById(R.id.min_line);
        dayBtn=(Button)findViewById(R.id.day_line);
        weekBtn=(Button)findViewById(R.id.week_line);
        monthBtn =(Button)findViewById(R.id.month_line);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        stock_name=(TextView)findViewById(R.id.stock_name);
        stock_id=(TextView)findViewById(R.id.stock_id);
        stock_name.setText(getIntent().getStringExtra("name"));
        stock_id.setText(getIntent().getStringExtra("id"));
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
        viewPager.setCurrentItem(getIntent().getIntExtra("index", 0));
    }

    private void initEvent(){
        minBtn.setOnClickListener(this);
        dayBtn.setOnClickListener(this);
        weekBtn.setOnClickListener(this);
        monthBtn.setOnClickListener(this);
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
    public void onFragmentInteraction(Uri uri) {

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
        }
    }
}
