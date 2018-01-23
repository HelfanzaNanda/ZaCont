package com.example.elfann.zakatcountapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplassBar extends AppCompatActivity {

    private static int splass_timeOut = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splass_bar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplassBar.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, splass_timeOut);

    }
}
