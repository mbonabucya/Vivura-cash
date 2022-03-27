package com.example.vivuracash;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.content.Context;



public class Persoanl_Report_adapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    public Persoanl_Report_adapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Personal_income_report_fragment income_report_Fragment = new Personal_income_report_fragment();
                return income_report_Fragment;
            case 1:
                Personal_expense_report expenses_report_Fragment = new Personal_expense_report();
                return expenses_report_Fragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {

        return totalTabs;
    }
}


