package com.example.android.redroom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager generalViewPager = (ViewPager) findViewById(R.id.general_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        generalViewPager.setAdapter(fragmentAdapter);

        TabLayout generalTabLayout = (TabLayout) findViewById(R.id.general_tab_layout);
        generalTabLayout.setupWithViewPager(generalViewPager);

        ImageView menuRedRoom = (ImageView) findViewById(R.id.menu_red_room);
        menuRedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(0, true);
            }
        });

        ImageView menuBrowse = (ImageView) findViewById(R.id.menu_browse);
        menuBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(1, true);
            }
        });

        ImageView menuCollection = (ImageView) findViewById(R.id.menu_collection);
        menuCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(2, true);
            }
        });

        ImageView menuBorrow = (ImageView) findViewById(R.id.menu_borrow);
        menuBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalViewPager.setCurrentItem(3, true);
            }
        });
    }
}
