package com.example.fyp;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    DrawerLayout drawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
   setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            String link = extras.getString(Intent.EXTRA_TEXT);
            Toast.makeText(this, link, Toast.LENGTH_SHORT).show();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("link", link);
            clipboard.setPrimaryClip(clip);
        }
   drawer= findViewById(R.id.drawer_id);
   ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
           R.string.nav_drawer_open, R.string.nav_drawer_close);
   drawer.addDrawerListener(toggle);
   toggle.syncState();
   toggle.getDrawerArrowDrawable();

   NavigationView navigationView= findViewById(R.id.nav_view);
   navigationView.setNavigationItemSelectedListener(this);

        tabLayout =  findViewById(R.id.tablayout_id);
          viewPager =  findViewById(R.id.viewpager_id);

          setupViewPager(viewPager);


        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite_black_24dp);

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                drawer.closeDrawers();
                break;
           /* case R.id.nav_signin:
                startActivity(new Intent(MainActivity.this, YoutubeSignInActivity.class));
                drawer.closeDrawers();
                break;*/
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                drawer.closeDrawers();
                break;
            case R.id.exit:
                finishAffinity();
                System.exit(0);

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.END);
        }
        else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item1:
                startActivity(new Intent(MainActivity.this, DownloadActivity.class));

                return true;

                default:
            return super.onOptionsItemSelected(item);
        }}



    public void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentHome(),"");
        adapter.AddFragment(new FragmentFav(),"");
        viewPager.setAdapter(adapter);

    }

}
