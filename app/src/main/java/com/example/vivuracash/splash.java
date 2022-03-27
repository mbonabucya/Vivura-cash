package com.example.vivuracash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class splash extends Activity {
    Handler han;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        han = new Handler() ;
        han.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
