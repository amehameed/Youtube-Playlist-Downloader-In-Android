package com.example.videodownloader;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> IstFragment = new ArrayList<>();
    private final List<String> IstTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return IstFragment.get(i);
    }

    @Override
    public int getCount() {
        return IstFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return IstTitles.get(position);
    }


    public void AddFragment(Fragment fragment , String title)
    {
        IstFragment.add(fragment);
        IstTitles.add(title);
    }
}
