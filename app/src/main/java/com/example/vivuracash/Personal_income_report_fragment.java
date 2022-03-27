package com.example.vivuracash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Personal_income_report_fragment extends Fragment {

    private RecyclerView recyclerView;
    private Income_report_rv_adapter adapter;
    private ArrayList<Income_Personal_Model> incomeModalList;
    DatabaseHelper db;
    private SearchView search;
    Button view_incomes;
    private Context ctx;
    private TextView NetIncome;

    public Personal_income_report_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =LayoutInflater.from(getContext()).inflate(R.layout.activity_personal_income_report_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_personal);
        search= view.findViewById(R.id.search_report);


        recyclerView.setHasFixedSize(true);
        incomeModalList=new ArrayList<>();
        db= new DatabaseHelper(view.getContext());
        incomeModalList= db.Show_personal_income();
        adapter=new Income_report_rv_adapter(incomeModalList, getActivity());
        LinearLayoutManager l= new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }



        });

        return view;

    }

    private void filter(String text) {

        ArrayList<Income_Personal_Model> filteredlist = new ArrayList<>();
        for (Income_Personal_Model item : incomeModalList) {
            if (item.getReason().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()){

            Toast.makeText(getContext(), "No data related found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filteredlist);
        }
    }

}