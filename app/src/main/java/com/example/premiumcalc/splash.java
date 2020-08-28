package com.example.premiumcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent i = new Intent(getBaseContext(), Home.class);
                    startActivity(i);

                    finish();
                } catch (InterruptedException e) {

                }
            }
        };
        background.start();

    }

}