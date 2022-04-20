package com.example.vivuracash;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    //List<businessModel> MybusinessList;
    businessAdapter adapter;
    LinearLayoutManager layoutManager;
    private FloatingActionButton fab_btn;
    DatabaseHelper db;
    private ArrayList<businessModel> MybusinessList;
    private Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_layout);
        fab_btn=findViewById(R.id.fab);
        db = new DatabaseHelper(this);

        initData();
        initRecylerview();


        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBusiness();
            }
        });
    }

    private void addBusiness() {
        AlertDialog.Builder myDialog= new AlertDialog.Builder(this);
        LayoutInflater inflater =LayoutInflater.from(this);
        View myView =inflater.inflate(R.layout.business_input,null);
        myDialog.setView(myView);

        final AlertDialog dialog =myDialog.create();
        dialog.setCancelable(true);

        final EditText businessName=myView.findViewById(R.id.biz_name);
        final EditText biz_contact= myView.findViewById(R.id.biz_contact);
        final Button save=myView.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myBusiness= businessName.getText().toString();
                String myContact= biz_contact.getText().toString();

                if(TextUtils.isEmpty(myBusiness)||TextUtils.isEmpty(myContact)){
                    businessName.requestFocus();
                    businessName.setError("Business name is required");
                    biz_contact.requestFocus();
                    biz_contact.setError("Business contact is required");

                }
                else if(!validateMobile(myContact)){

                    biz_contact.setError("this phone is invalid, use different one");

                }
                else{

                    Boolean insert =db.add_biz(myBusiness,myContact);
                    if(insert){
                        Toast.makeText(getApplicationContext()," business is added successfully",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        startActivity(getIntent());
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Adding business is failed ",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        dialog.show();
    }

    private void initData() {

        MybusinessList =new ArrayList<>();
        db = new DatabaseHelper(this);
        MybusinessList= db.readBusiness();

//        MybusinessList.add(new businessModel("Katheos Group","+250783784590"));
//        MybusinessList.add(new businessModel("Jtech ltd","+250783783267"));
//        MybusinessList.add(new businessModel("WEC","+250788246645"));
//        MybusinessList.add(new businessModel("ADls","+2507837887"));
    }

    private void initRecylerview() {
        recyclerView =findViewById(R.id.recyclerview1);
        layoutManager =new LinearLayoutManager(this );
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new businessAdapter(MybusinessList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    Boolean validateMobile(String input){

        if(input.length()>10){

        }
        Pattern p= Pattern.compile("[0][0-9]{9,13}");
        Matcher m= p.matcher(input);
        return m.matches();
    }



}










