package com.example.vivuracash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class income_view_test extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Income_report_rv_adapter adapter;
    private ArrayList<Income_Personal_Model> incomeModalList;
    DatabaseHelper db;
    String ULR ="http://127.0.0.1:8000/CashFlows/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_view_test);
        db =new DatabaseHelper(this );
        recyclerView=findViewById(R.id.recycler_personal);
        incomeModalList=new ArrayList<>();
        incomeModalList= db.Show_personal_income();
        adapter=new Income_report_rv_adapter(incomeModalList,income_view_test.this);
        LinearLayoutManager l= new LinearLayoutManager(income_view_test.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);



       // String currentUser= db.checkUsername();

 /*
        Cursor cursor =db.alldata();
        if (cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "No data available ", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext())
            {


                Toast.makeText(getApplicationContext(), "Amount: "+cursor.getString(1), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "payment mode: "+cursor.getString(3), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "reason: "+cursor.getString(2), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "date: "+cursor.getString(4), Toast.LENGTH_SHORT).show();

            }

        }
        */





    }
}