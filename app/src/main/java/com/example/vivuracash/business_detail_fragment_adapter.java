package com.example.vivuracash;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class business_detail_fragment_adapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public business_detail_fragment_adapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                business_income incomeFragment = new business_income();
                return incomeFragment;
            case 1:
                business_expenses expensesFragment = new business_expenses();
                return expensesFragment;
            case 2:
                business_report reportFragment = new business_report();
                return reportFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {

        return totalTabs;
    }
}



