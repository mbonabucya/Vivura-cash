package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {
    EditText firstName,LastName,Phone,Password, Confirm_password;
    Button register,loginbtn;
    DatabaseHelper db;
    char[] otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        firstName=findViewById(R.id.fname);
        LastName=findViewById(R.id.lname);
        Phone=findViewById(R.id.phoneNumber);
        Password=findViewById(R.id.password);
        Confirm_password=findViewById(R.id.password2);
        register=findViewById(R.id.register);
        loginbtn= findViewById(R.id.login);
        db = new DatabaseHelper(this);


        //Validate fname

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String fname=firstName.getText().toString();
                if(ValidateFname(firstName.getText().toString())){
                    firstName.setError("This is filed is required");
                }
                else if(!fname.matches("[a-zA-Z ]+"))
                {
                    firstName.requestFocus();
                    firstName.setError("please enter text only");
                }
                else if(fname.length()<4){
                    firstName.requestFocus();
                    firstName.setError("at least 4 characters are required");

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        LastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String lname =LastName.getText().toString();
                 if (!lname.matches("[a-zA-Z ]+")){
                    LastName.requestFocus();
                    LastName.setError("please enter text only");

                }
                 else if(lname.length()<4){
                     LastName.requestFocus();
                     LastName.setError("at least 4 characters are required");

                 }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Validate password length

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass =Password.getText().toString();
                 if(pass.length()<8)
                {
                    Password.requestFocus();
                    Password.setError("at least 8 characters,combination of Capital letter , numbers , and special characters are required ");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //Validate Pass matches

        Confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   if(ValidatePass(Password.getText().toString(),Confirm_password.getText().toString())){
                       register.setEnabled(true);
                   }

                   else
                   {
                       Confirm_password.setError("Passwords should match");
                   }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        //Validate Phone number

        Phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (validateMobile(Phone.getText().toString())){
                    register.setEnabled(true);
                }
                else
                {
                    register.setEnabled(false);
                    Phone.setError("Invalid Phone number");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                   // Generate OTP SMS

                Random random = new Random();
                otp = new char[4];
                for (int i=0; i<4; i++)
                {
                    otp[i]= (char)(random.nextInt(10)+25);
                }
                //        Toast.makeText(getApplicationContext(), String.valueOf(otp), Toast.LENGTH_SHORT).show();


                String fname= firstName.getText().toString();
                String lname =LastName.getText().toString();
                String phone =Phone.getText().toString();
                String pass= Password.getText().toString();
                String repass= Confirm_password.getText().toString();


                if (TextUtils.isEmpty(fname)||TextUtils.isEmpty(lname)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass))
                {
                    Toast.makeText(registerActivity.this,"All fields are required",Toast.LENGTH_LONG).show();
                }

                else
                {
                    if (pass.equals(repass)){
                        Boolean checkUser= db.checkUsername(phone);
                        if (checkUser==false){
                            Boolean insert =db.InsertData(phone,pass,fname,lname);
                            if(insert==true){
                                Toast.makeText(registerActivity.this," User registered",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(getApplicationContext(),loginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(registerActivity.this,"registration failed ",Toast.LENGTH_SHORT).show();
                            }

                        }//
                        else{
                            Phone.setError("This phone number is already taken, try with different number");
                            Toast.makeText(registerActivity.this,"Phone number is already taken",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(registerActivity.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                    }

                }//else
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
            }
        });

    }

    Boolean validateMobile(String input){

        if(input.length()>10){

        }
        Pattern p= Pattern.compile("[0][0-9]{9,13}");
        Matcher m= p.matcher(input);
        return m.matches();
    }
    Boolean ValidatePass(String pass1,String pass2){
        if(!pass1.equals(pass2)){
            return false;
        }
        else
        {
            return true;
        }
    }

    Boolean ValidateFname(String fname){
        if (fname.length()==0){
            return true;

        }
        else
        {
            return false;
        }
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


}
