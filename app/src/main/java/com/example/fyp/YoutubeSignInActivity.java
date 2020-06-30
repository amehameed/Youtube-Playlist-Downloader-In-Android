package com.example.fyp;

//import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class YoutubeSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
