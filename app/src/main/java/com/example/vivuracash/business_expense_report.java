package com.example.vivuracash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class business_expense_report extends Fragment{
    private RecyclerView recyclerView;
    private business_report_expense_rv_adapter adapter;
    private ArrayList<business_expenses_model> ExpenseModalList;
    DatabaseHelper db;
    private SearchView search;
    private Context ctx;


    public business_expense_report() {
        //empty constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.activity_personal_expense_report, container, false);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_business_expense_report, container, false);
        recyclerView = view.findViewById(R.id.recycler_personal_expense);
        search = view.findViewById(R.id.search_report_expense);
        recyclerView.setHasFixedSize(true);
        ExpenseModalList = new ArrayList<>();
        db = new DatabaseHelper(view.getContext());
        String businessName=getActivity().getIntent().getStringExtra("business_name");
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String uid = sh.getString("user_id", "");

        ExpenseModalList = db.Show_business_Expenses(uid,businessName);
        adapter = new business_report_expense_rv_adapter(ExpenseModalList, getActivity());
        LinearLayoutManager l = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);


return view;
    }
}