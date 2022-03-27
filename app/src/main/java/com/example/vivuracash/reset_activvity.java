package com.example.vivuracash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reset_activvity extends AppCompatActivity {
    EditText old_password, new_password,reset_phone;
    Button reset_pass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

      reset_phone=findViewById(R.id.reset_pass_phone);
      old_password=findViewById(R.id.old_password);
      new_password = findViewById(R.id.New_password);
      reset_pass= findViewById(R.id.save_pass);
      db = new DatabaseHelper(this);

        Intent intent = getIntent();
        reset_phone.setText(intent.getStringExtra("Phonenumber"));





reset_pass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String user= reset_phone.getText().toString();
        String pass= old_password.getText().toString();
        String repass =new_password.getText().toString();

        if(pass.equals(repass)) {
            Boolean checkpasswordupdate = db.updatepass(user, pass);
            if (checkpasswordupdate == true) {
                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(reset_activvity.this, "Password is not updated", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(reset_activvity.this, "Password must match", Toast.LENGTH_SHORT).show();
        }

        //save a new pass into database
    }
});

    }
}