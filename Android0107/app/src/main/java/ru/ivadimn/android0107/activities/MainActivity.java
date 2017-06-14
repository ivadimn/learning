package ru.ivadimn.android0107.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import ru.ivadimn.android0107.R;
import ru.ivadimn.android0107.adapters.FragmentPagerAdapter;
import ru.ivadimn.android0107.fragments.PersonListFragment;

public class MainActivity extends AppCompatActivity implements PersonListFragment.OnSelectItemListener {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("New Title");

        adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PersonListFragment.getFragment());
        viewPager = (ViewPager) findViewById(R.id.fragment_pager_id);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSelectItem(int position) {
        Toast.makeText(this, "Position " + position, Toast.LENGTH_SHORT).show();
    }
}
