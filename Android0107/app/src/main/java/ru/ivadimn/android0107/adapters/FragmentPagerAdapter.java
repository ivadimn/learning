package ru.ivadimn.android0107.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 14.06.2017.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> pages = new ArrayList<>();

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addFragment(Fragment f) {
        pages.add(f);
    }
}
