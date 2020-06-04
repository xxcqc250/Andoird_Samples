package com.example.slideviewpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    ArrayList<View> pagerList;
    private ViewGroup group;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.ViewPage);

        LayoutInflater li = getLayoutInflater().from(this);
        View v1 = li.inflate(R.layout.layout1,null);
        View v2 = li.inflate(R.layout.layout2,null);
        pagerList = new ArrayList<View>();
        pagerList.add(v1);
        pagerList.add(v2);


        myViewPagerAdapter pagerAdapter = new myViewPagerAdapter(pagerList);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);


        //viewPager下面的圓點，ViewGroup
        ImageView[] tips = new ImageView[pagerList.size()];
        group = (ViewGroup)findViewById(R.id.viewGroup);
        for(int i =0;i<pagerList.size();i++){
            imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
            imageView.setPadding(20, 0, 20, 0);
            tips[i] = imageView;

            //預設第一張圖顯示為選中狀態
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }

            group.addView(tips[i]);
        }
        pager.addOnPageChangeListener(new GuidePageChangeListener(tips));
    }
}
