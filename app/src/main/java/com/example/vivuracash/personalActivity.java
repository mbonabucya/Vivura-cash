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

    String userId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        userId=getIntent().getStringExtra("user_id");
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
              Intent intent =new Intent(personalActivity.this,CashIn.class);
              userId=getIntent().getStringExtra("user_id");
              intent.putExtra("user_id",userId);
              startActivity(intent);
          }
      });
      expensebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(personalActivity.this,Cashout.class);
              userId=getIntent().getStringExtra("user_id");
              intent.putExtra("user_id",userId);
              startActivity(intent);
          }
      });
      reportbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(personalActivity.this,Report.class);
              intent.putExtra("user_id",userId);
              System.out.println("Numero yawe ni :"+userId);
              startActivity(intent);
          }
      });

        System
                .out.println(
                        "IYI NIYO USER ID kumu User"+userId
        );

        userId=getIntent().getStringExtra("user_id");

        netInc.setText("+" + db.TotalIncome(userId) +" "+ "RWF");
        netBal.setText("-"+ db.TotalExpense(userId)+" "+"Rwf");
        profit.setText(""+db.TotalBalance(userId)+" "+"RWF");



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