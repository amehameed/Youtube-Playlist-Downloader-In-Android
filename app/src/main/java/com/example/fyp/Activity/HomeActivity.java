package com.example.fyp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.fyp.R;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
//    BubbleNavigationLinearView bubbleNavigation;
    SmoothBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        bubbleNavigation=findViewById(R.id.top_navigation_constraint);
//        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
//            @Override
//            public void onNavigationChanged(View view, int position) {
//                Toast.makeText(getApplicationContext(),"Position "+position,Toast.LENGTH_SHORT);
//            }
//        });
        bottomBar=findViewById(R.id.bottomBar);
        String status = null;

    }

    @Override
    public void onClick(View v) {

    }
}
