package com.example.kLine_fragment.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class myViewPager extends ViewPager {
    public myViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public myViewPager(Context context){
        this(context,null);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
