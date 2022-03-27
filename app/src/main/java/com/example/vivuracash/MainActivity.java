package com.example.vivuracash;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username= (TextView)findViewById(R.id.editTextPhone);
        TextView password = (TextView)findViewById(R.id.password);
        Button loginbtn =(Button ) findViewById(R.id.loginbtn);
        //MaterialButton register =(MaterialButton) findViewById(R.id.register);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //username:admin & password:admin
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "Incorrect username or Passord", Toast.LENGTH_SHORT).show();
                }


            }
        });

     /*   register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "you will be registered later", Toast.LENGTH_SHORT).show();
            }
        });*/
//public void Onclick(){}



    }


    public void dosomething(View view) {

            Intent i =new Intent(this,registerActivity.class);
            startActivity(i);

    }
}
