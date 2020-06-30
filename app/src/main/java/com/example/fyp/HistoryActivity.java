package com.example.fyp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
