package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class loginActivity extends  AppCompatActivity {
    EditText user,password;
    Button register,loginbtn;
    DatabaseHelper db;
    TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.editTextPhone);
        password =  findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        register =  findViewById(R.id.register);
        forgotpassword =findViewById(R.id.forgotpass);
        db = new DatabaseHelper(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username =user.getText().toString();
                String pass =password.getText().toString();

                if (TextUtils.isEmpty(username)|| TextUtils.isEmpty(pass)){

                    Toast.makeText(loginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean CheckUserPass= db.checkUsernamePassword(username,pass);
                    if (CheckUserPass==true){
                        Toast.makeText(loginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),homebridge.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(loginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),registerActivity.class);
                startActivity(intent);

            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(),Password_activity.class);
                startActivity(intent);
            }
        });



    }




}


