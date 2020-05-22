package ru.ivadimn.notescompanion.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.adapters.MainPagerAdapter;
import ru.ivadimn.notescompanion.fragments.ContactsFragment;
import ru.ivadimn.notescompanion.fragments.GestureFragment;
import ru.ivadimn.notescompanion.fragments.NoteFragment;
import ru.ivadimn.notescompanion.fragments.PagerFragment;
import ru.ivadimn.notescompanion.fragments.PictureFragment;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainPagerAdapter pagerAdapter;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.page_title);

        fragmentManager = getSupportFragmentManager();
        tabLayout = (TabLayout) findViewById(R.id.tab_id);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager = (ViewPager) findViewById(R.id.fragment_pager);
        pagerAdapter = new MainPagerAdapter(fragmentManager);
        PagerFragment pf = new NoteFragment();
        pf.setTitle(titles[0]);
        pagerAdapter.addPage(pf, 0);
        pf = new PictureFragment();
        pf.setTitle(titles[1]);
        pagerAdapter.addPage(pf, 1);

        pf = new GestureFragment();
        pf.setTitle(titles[2]);
        pagerAdapter.addPage(pf, 2);

        pf = new ContactsFragment();
        pf.setTitle(titles[3]);
        pagerAdapter.addPage(pf, 3);


        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_notes);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.sym_def_app_icon);
    }
}
