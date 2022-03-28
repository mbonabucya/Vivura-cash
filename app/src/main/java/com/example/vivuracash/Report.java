package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Report extends AppCompatActivity {

    private ImageButton back_btn;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView netbalances;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        db=new DatabaseHelper(this);
        netbalances= findViewById(R.id.balance);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Show Incomes"));
        tabLayout.addTab(tabLayout.newTab().setText("Show Expenses"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final Persoanl_Report_adapter adapter = new Persoanl_Report_adapter(this,getSupportFragmentManager(),
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
        
        back_btn =findViewById(R.id.backButton);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),personalActivity.class);
                startActivity(intent);
            }
        });

        String userId=getIntent().getStringExtra("user_id");
        netbalances.setText(" "+db.TotalBalance(userId)+"RWF");
    }
}
