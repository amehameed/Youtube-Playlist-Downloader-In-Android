package com.example.videodownloader;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class DownloadActivity extends AppCompatActivity {
    private static ViewPagerAdapter adapter;
    public int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);//Set up View Pager

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);//setting tab over viewpager
        tabLayout.getTabAt(0).setIcon(R.drawable.youtube);
        tabLayout.getTabAt(1).setIcon(R.drawable.fb);
        tabLayout.getTabAt(2).setIcon(R.drawable.insta);


    }


    //Setting View Pager
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new YoutubeDownload_Fragment(), "YouTue");
        adapter.AddFragment(new FacebookDownload_Fragment(), "Facebook");
        adapter.AddFragment(new IDownload_Fragment(), "Instagram");

        viewPager.setAdapter(adapter);
    }


    //Return current fragment on basis of Position
    public Fragment getFragment(int pos) {
        return adapter.getItem(pos);
    }

    public void openFolder(View view)
    {
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }

}
