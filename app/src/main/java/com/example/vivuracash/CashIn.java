package com.example.vivuracash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;


public class CashIn extends AppCompatActivity {

    EditText amount,reason,item;
    Button save,cancel;
    DatabaseHelper db;
    private TextView NetIncome;
    private ImageButton back_btn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashin_layout);
        Spinner spinnering=findViewById(R.id.payment_spinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.payment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnering.setAdapter(adapter);
   amount= findViewById(R.id.editTextNumberDecimal);
   reason= findViewById(R.id.editTextTextMultiLine);
   save =findViewById(R.id.save);
   cancel=findViewById(R.id.cancel);
   item=findViewById(R.id.item);
   back_btn =findViewById(R.id.backButton);

   db=new DatabaseHelper(this);

   save.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String amt=amount.getText().toString();
           int finalamount=Integer.parseInt(amt);
           String reseaux=reason.getText().toString();
           String spin= spinnering.getAdapter().toString();
           String activity=item.getText().toString();
///if spin doesnot allow to be added , I will change to edit text
           if (TextUtils.isEmpty(amt)||TextUtils.isEmpty(reseaux)||TextUtils.isEmpty(spin)|TextUtils.isEmpty(activity)){
               amount.setError("this is field is required");
               reason.setError("this is field is required");
           }
           else if(!reseaux.matches("[a-zA-Z ]+"))
           {
               reason.requestFocus();
               reason.setError("please enter text only");
           }
           else{
               String userId=getIntent().getStringExtra("user_id"
               );
               Boolean insert =db.add_income(finalamount,reseaux,spin,activity,userId);
               if(insert==true){
                   Toast.makeText(CashIn.this," Income added successfully",Toast.LENGTH_SHORT).show();
                   Intent intent =new Intent(getApplicationContext(),CashIn.class);
                   startActivity(intent);
               }
               else
               {
                   Toast.makeText(CashIn.this,"Adding Income is failed ",Toast.LENGTH_SHORT).show();
               }

           }
       }
   });


   cancel.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i =new Intent(CashIn.this,Report.class);
           startActivity(i);
       }
   });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent(getApplicationContext(),personalActivity.class);
                startActivity(inte);
            }
        });




    }
}
