package com.example.foldupthequilt;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;


import androidx.annotation.NonNull;


public class tab extends TabActivity implements  TabHost.OnTabChangeListener {
    int image[] = {R.drawable.main, R.drawable.click_main, R.drawable.signed, R.drawable.unsigned
            };
    private TabHost tabHost;

    private SignCalendarReq signCalendarReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost_layout);
        tabHost = getTabHost();
        tabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线


        TabHost.TabSpec tab_main = tabHost.newTabSpec("0");
        tab_main.setIndicator(getImageView(1));
        tab_main.setContent(new Intent(this, MainActivity.class));

        TabHost.TabSpec tab_functions = tabHost.newTabSpec("1");
        tab_functions.setIndicator(getImageView(3));
        tab_functions.setContent(new Intent(this, I8ShowSignCalendarActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));


        tabHost.addTab(tab_main);
        tabHost.addTab(tab_functions);

        tabHost.setOnTabChangedListener(this);


        tabHost.setCurrentTab(0);
        setContentView(tabHost);

    }

    private View getImageView(int index) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
        ImageView imageView = view.findViewById(R.id.tab_iv_image);
        imageView.setImageResource(image[index]);
        return view;
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onTabChanged(String tabId) {
        if (tabId.equals("0")) {
            ImageView iv = tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_iv_image);
            iv.setImageDrawable(getResources().getDrawable(R.drawable.click_main));
            iv = tabHost.getTabWidget().getChildAt(1).findViewById(R.id.tab_iv_image);
            iv.setImageDrawable(getResources().getDrawable(R.drawable.unsigned));

        } else if (tabId.equals("1")) {
            ImageView iv = tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_iv_image);
            iv.setImageDrawable(getResources().getDrawable(R.drawable.main));
            iv = tabHost.getTabWidget().getChildAt(1).findViewById(R.id.tab_iv_image);
            iv.setImageDrawable(getResources().getDrawable(R.drawable.signed));

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {//还原字体大小
        Resources res = super.getResources();
        //非默认值
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
