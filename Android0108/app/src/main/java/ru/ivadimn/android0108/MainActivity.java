package ru.ivadimn.android0108;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ru.ivadimn.android0108.fragments.NavigationFragment;
import ru.ivadimn.android0108.fragments.OtherFragment;
import ru.ivadimn.android0108.fragments.TwoFragments;
import ru.ivadimn.android0108.model.Repository;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private String appName;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName = getString(R.string.app_name);

        toolbar  = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        navigationView = (NavigationView) findViewById(R.id.navigation_id);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
            toolbar.setTitle(appName);
        }
        else
            super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home :
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavigationFragment fragment;
        switch(item.getItemId()) {
            case R.id.item_list_drawer_id:
                fragment = TwoFragments.getInstance(Repository.DRAWERS);
                fragment.setTitle(getString(R.string.drawers));
                showFragment(fragment);
                break;
            case R.id.item_list_planet_id:
                fragment = TwoFragments.getInstance(Repository.PLANETS);
                fragment.setTitle(getString(R.string.planets));
                showFragment(fragment);
                break;
            case R.id.item_other_id:
                fragment = new OtherFragment();
                fragment.setTitle(getString(R.string.other));
                showFragment(fragment);
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    private void showFragment(NavigationFragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container_id, fragment)
                .addToBackStack(fragment.getTitle())
                .commit();
    }
}
