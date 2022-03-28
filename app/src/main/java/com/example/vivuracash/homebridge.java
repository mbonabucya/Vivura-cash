package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class homebridge extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homebridge);

        TextView text;
        Button business,personal;

        business =findViewById(R.id.businessbridge);
        personal  =findViewById(R.id.personalbridge);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),BusinessActivity.class);
                startActivity(intent);
            }
        });
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=getIntent().getStringExtra("phoneId");
                System.out.println("Numero yawe ni :"+id);
                Intent intent =new Intent(getApplicationContext(),personalActivity.class);
                intent.putExtra("userId",id);
                startActivity(intent);

            }
        });

    }
}