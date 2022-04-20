package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Password_activity extends AppCompatActivity {
    Button resetpass;
    TextView Phonenumber;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);

        resetpass = findViewById(R.id.reset_btn);
        Phonenumber = findViewById(R.id.pass_username);
        db= new DatabaseHelper(this);

        Phonenumber.setText(getIntent().getStringExtra("phoneId"));
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=Phonenumber.getText().toString();
                Boolean checkPhone =db.checkUsername(user);

                if (checkPhone==true) {
                    Intent intent = new Intent(getApplicationContext(), reset_activvity.class);
                    intent.putExtra("Phonenumber",user);
                    startActivity(intent);
                }
                else
                    {
                    Toast.makeText(Password_activity.this, "Phone number does not exist", Toast.LENGTH_SHORT).show();
                    //username doesnot exist
                }
            }
        });
    }
}