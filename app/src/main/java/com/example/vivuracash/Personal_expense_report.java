package com.example.vivuracash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Personal_expense_report extends Fragment{
    private RecyclerView recyclerView;
    private personal_expense_rv_adapter adapter;
    private ArrayList<personal_expenses_Model> ExpenseModalList;
    DatabaseHelper db;
    private SearchView search;
    private Context ctx;


    public Personal_expense_report() {
        //empty constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.activity_personal_expense_report, container, false);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_personal_expense_report, container, false);
        recyclerView = view.findViewById(R.id.recycler_personal_expense);
        search = view.findViewById(R.id.search_report_expense);
        recyclerView.setHasFixedSize(true);
        ExpenseModalList = new ArrayList<>();
        db = new DatabaseHelper(view.getContext());
        Report rpt=(Report)getActivity();
        String userId=rpt.getUserId();
        ExpenseModalList = db.Show_personal_Expenses(userId);
        adapter = new personal_expense_rv_adapter(ExpenseModalList, getActivity());
        LinearLayoutManager l = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);


return view;
    }
}