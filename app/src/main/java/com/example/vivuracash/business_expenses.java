package com.example.vivuracash;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.SharedPreferences;
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

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class business_expenses extends Fragment implements View.OnClickListener {

    EditText amount,reason,item;
    Button save,cancel;
    DatabaseHelper db;
    private TextView NetIncome;
    private ImageButton back_btn;
    Spinner spinner;

    String userId;
    String businesName;

    public business_expenses() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_expense, container, false);

        spinner=view.findViewById(R.id.payment_spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(), R.array.payment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        amount= view.findViewById(R.id.amount);
        reason= view.findViewById(R.id.reason);
        save =view.findViewById(R.id.save);
        cancel=view.findViewById(R.id.cancel);
        item=view.findViewById(R.id.category);
//        back_btn =view.findViewById(R.id.backButton);

        db=new DatabaseHelper(getContext());

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.save:
                businesName=getActivity().getIntent().getStringExtra("business_name");
                String amt=amount.getText().toString();
                System.out.println(
                        "amafarannga abikijwe  angana :"+amt
                );
                int expense=Integer.parseInt(amt);
                String reseaux=reason.getText().toString();

                String spin= spinner.getAdapter().toString();
                String action=item.getText().toString();
///if spin doesnot allow to be added , I will change to edit text
                if (TextUtils.isEmpty(amt)||TextUtils.isEmpty(reseaux)||TextUtils.isEmpty(spin)|TextUtils.isEmpty(action)){
                    amount.setError("this  field is required");
                    reason.setError("this field is required");

                }
                else if(!reseaux.matches("[a-zA-Z,+ ]+"))
                {
                    reason.requestFocus();
                    reason.setError("please enter text only");
                }
                else{
//                    String user_id=getIntent().getStringExtra("user_id");
                    // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
                    SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                    String uid = sh.getString("user_id", "");

                    Boolean insert =db.add_biz_expenses(expense,reseaux,spin,action,uid,businesName);
                    if(insert){
                        Toast.makeText(getContext()," expense added successfully",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getContext()
                                ,"Adding expenses is failed ",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.cancel:
                //cancel
                break;
        }
    }
}