package com.example.vivuracash;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cashout extends AppCompatActivity {
Button save_btn , cancel_btn;
EditText cashout_amt, cashout_reason,activity;
private ImageButton  back_btn;
    String  userId;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashout_layout);
         userId=getIntent().getStringExtra("user_id");
        Spinner spinner=findViewById(R.id.payment_spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.payment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        cashout_amt=findViewById(R.id.editTextNumberDecimal);
        cashout_reason=findViewById(R.id.editTextTextMultiLine);
        activity=findViewById(R.id.activity);
        save_btn =findViewById(R.id.save);
        cancel_btn =findViewById(R.id.cancel);
        back_btn =findViewById(R.id.backButton);
        db =new DatabaseHelper(this);


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amt=cashout_amt.getText().toString();
                int expense=Integer.parseInt(amt);
                String reseaux=cashout_reason.getText().toString();
                String spin= spinner.getAdapter().toString();
                String action=activity.getText().toString();
///if spin doesnot allow to be added , I will change to edit text
                if (TextUtils.isEmpty(amt)||TextUtils.isEmpty(reseaux)||TextUtils.isEmpty(spin)|TextUtils.isEmpty(action)){
                    cashout_amt.setError("this  field is required");
                    cashout_reason.setError("this field is required");

                }
                else if(!reseaux.matches("[a-zA-Z,+ ]+"))
                {
                    cashout_reason.requestFocus();
                    cashout_reason.setError("please enter text only");
                }
                else{
                    String user_id=getIntent().getStringExtra("user_id");
                    Boolean insert =db.add_expenses(expense,reseaux,spin,action,user_id);
                    if(insert){
                        Toast.makeText(Cashout.this," expense added successfully",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(Cashout.this,"Adding expenses is failed ",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

cancel_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent inte=new Intent(Cashout.this,Report.class);
        inte.putExtra("user_id",userId);
        startActivity(inte);
    }
});
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent(getApplicationContext(),personalActivity.class);
                inte.putExtra("user_id",userId);
                startActivity(inte);
            }
        });
    }
    /*Boolean validateAmount(int input){

        if(input.>6){
            return false;

        }
        Pattern p= Pattern.compile("[1-9][0-9]{6}");
        Matcher m= p.matcher(input);
        return m.matches();
    }*/
}
