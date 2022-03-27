package com.example.vivuracash;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class personalActivity extends AppCompatActivity {
    Button incomebtn,expensebtn,reportbtn;
    private TextView netInc,netBal,profit;
    DatabaseHelper db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);

Toolbar tBar =(Toolbar)findViewById(R.id.mytoolbar);
setSupportActionBar(tBar);

      incomebtn=(Button) findViewById(R.id.cashin);
      expensebtn=(Button) findViewById(R.id.cashout);
      reportbtn=(Button) findViewById(R.id.report);
      netInc =findViewById(R.id.netIncome);
      netBal= findViewById(R.id.netBalance);
      profit=findViewById(R.id.netProfit);
      db= new DatabaseHelper(this);

      incomebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i =new Intent(personalActivity.this,CashIn.class);
              startActivity(i);
          }
      });
      expensebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i =new Intent(personalActivity.this,Cashout.class);
              startActivity(i);
          }
      });
      reportbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i =new Intent(personalActivity.this,Report.class);
              startActivity(i);
          }
      });


        netInc.setText("+" + db.TotalIncome() +" "+ "RWF");
        netBal.setText("-"+ db.TotalExpense()+" "+"Rwf");
        profit.setText(""+db.TotalBalance()+" "+"RWF");
         /*
        Cursor cursor =db.calculatingTotalIncome();
        if (cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "No data available ", Toast.LENGTH_SHORT).show();
        }
        else{

            netBal.setText(cursor.getCount());

        }
        */
    }




}