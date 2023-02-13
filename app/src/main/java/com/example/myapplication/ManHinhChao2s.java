package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ManHinhChao2s extends AppCompatActivity {
    int TIMEOUT = 2000;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao2s);
        intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChao2s.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },TIMEOUT);
    }
}