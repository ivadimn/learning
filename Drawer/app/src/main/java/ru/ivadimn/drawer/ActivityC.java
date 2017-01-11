package ru.ivadimn.drawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class ActivityC extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "ACTIVITY_C";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActionBar actionBar;
    FragmentList fragmentList  = new FragmentList();
    FragmentTwo fragmentTwo = new FragmentTwo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        navigationView = (NavigationView) findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(this);

        // Включаем значок у ActionBar для управления выдвижной панелью щелчком
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "Before start fragment");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.barsik_id:
               showFragment(fragmentList);
                setTitle(item.getTitle());
               break;
            case R.id.begemot_id:
                showFragment(fragmentTwo);
                setTitle(item.getTitle());
                break;
        }
        item.setChecked(true);
        return true;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
        drawerLayout.closeDrawer(navigationView);

    }

    @Override
    public void setTitle(CharSequence title) {
        if (actionBar != null)
            actionBar.setTitle(title);
        else
            super.setTitle(title);
    }
}
