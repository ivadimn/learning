package ru.ivadimn.notescompanion.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.notescompanion.fragments.PagerFragment;

/**
 * Created by vadim on 12.03.17.
 */

 public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public static final int PAGE_COUNT = 2;
    private List<PagerFragment> pages = new ArrayList<PagerFragment>(PAGE_COUNT);

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void addPage(PagerFragment page, int index) {
        pages.add(index, page);
    }
}
