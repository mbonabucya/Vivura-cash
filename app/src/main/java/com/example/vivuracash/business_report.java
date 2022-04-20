package com.example.vivuracash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class business_report extends Fragment{

    private ImageButton back_btn;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView netBalances;
    DatabaseHelper db;
    String uid;
    public business_report() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.businnness_report_layout, container, false);
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
         uid = sh.getString("user_id", "");


// We can then use the data


      //netbalances.setText(db.TotalBusinessBalance(uid));

        tabLayout =view.findViewById(R.id.tabLayout);
        viewPager =view.findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Show Incomes"));
        tabLayout.addTab(tabLayout.newTab().setText("Show Expenses"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final Business_Report_adapter adapter = new Business_Report_adapter(getContext(),getChildFragmentManager(),
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db=new DatabaseHelper(getContext());
        netBalances=view.findViewById(R.id.mybalance);
        String businessName=getActivity().getIntent().getStringExtra("business_name");
        netBalances.append(" "+db.TotalBusinessBalance(uid,businessName));
    }
}

