package com.example.slideviewpage;

import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

class GuidePageChangeListener implements ViewPager.OnPageChangeListener{
    private ImageView[] tips;

    GuidePageChangeListener(ImageView[] tips){
        this.tips = tips;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    //切換view時，下方圓點的變化。
    public void onPageSelected(int position) {
        tips[position].setBackgroundResource(R.mipmap.page_indicator_focused);
        //這個圖片就是選中的view的圓點
        for(int i=0;i<tips.length;i++){
            if (position != i) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
                //這個圖片是未選中view的圓點
            }
        }
    }
}
