package com.example.fyp;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity {
    private static ViewPagerAdapter adapter;
  ArrayList<String> vidTitle,url;
//    String url;
  int dID,length;
  String prog;
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
        vidTitle=new ArrayList<>();
        url=new ArrayList<>();

        vidTitle= (ArrayList<String>) getIntent().getSerializableExtra("videoTitle");
        url= (ArrayList<String>) getIntent().getSerializableExtra("url");
        prog=getIntent().getStringExtra("media");
        dID=getIntent().getIntExtra("id",0);
        length=getIntent().getIntExtra("length",0);


    }


    //Setting View Pager
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        Bundle bundle = new Bundle();
//        bundle.putString("videoTitle", vidTitle);
//        bundle.putInt("pId", dID);
//        bundle.putInt("length", length);
////        bundle.putInt("progress", prog);
//        url=getIntent().getStringArrayExtra("url");
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
//        bundle.putString("url123", url);
// set Fragmentclass Arguments
        YoutubeDownload_Fragment yFragment = new YoutubeDownload_Fragment();
//        yFragment.setArguments(bundle);
        adapter.AddFragment(yFragment, "YouTube");
        adapter.AddFragment(new FacebookDownload_Fragment(), "Facebook");
        adapter.AddFragment(new InstagramDownload_Fragment(), "Instagram");
        viewPager.setAdapter(adapter);
    }

    public ArrayList<String>  getStringUrlYoutube(){

        return url;
    }
    public ArrayList<String>  getStringUrlFacebook(){

        return url;
    }
    public ArrayList<String>  getStringTitleYoutube(){

        return vidTitle;
    }
    public ArrayList<String>  getStringTitleFacebook(){

        return vidTitle;
    }
    public ArrayList<String>  getStringUrlInsta(){

        return url;
    }
    public ArrayList<String>  getStringTitleInsta(){

        return vidTitle;
    }
//    public ArrayList<String> getTitleVideo(){
//
//        return  vidTitle;
//    }
    public String getMyfileProg() {
        return prog;
    }
    //Return current fragment on basis of Position
    public Fragment getFragment(int pos) {
        return adapter.getItem(pos);
    }

}
