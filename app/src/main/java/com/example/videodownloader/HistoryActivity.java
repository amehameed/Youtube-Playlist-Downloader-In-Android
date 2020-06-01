package com.example.videodownloader;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HistoryActivity extends AppCompatActivity {
    public TextView text;
    Context context;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        context=this;
        Paper.init(context);
        list=findViewById(R.id.history);
        int index= Paper.book().read("index");
        List<String> keys=Paper.book().getAllKeys();
        ArrayList<String> url = new ArrayList<String>();

        for(int i=1 ; i<index ; i++) {

            url.add((String) Paper.book().read(keys.get(i).toString()));
        }

        ArrayAdapter arrayAdapter= new ArrayAdapter(HistoryActivity.this,R.layout.support_simple_spinner_dropdown_item,url);
        list.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
