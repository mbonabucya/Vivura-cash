package com.example.vivuracash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class businessDetails extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    private TextView detail_businessName;
    private ImageView detail_logo;
    private Button income_detail,expense_detail,report_detail;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_detail_layout);
        String businessName=getIntent().getStringExtra("business_name");

        System.out.println(
                "business name is "+businessName
        );
        detail_businessName =findViewById(R.id.detail_txt);
        detail_logo=findViewById(R.id.detail_logo);
        detail_logo.setImageResource(getIntent().getIntExtra("logo",0));
        detail_businessName.setText(businessName);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("CASH IN"));
        tabLayout.addTab(tabLayout.newTab().setText("CASH OUT"));
        tabLayout.addTab(tabLayout.newTab().setText("REPORT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final business_detail_fragment_adapter adapter = new business_detail_fragment_adapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
