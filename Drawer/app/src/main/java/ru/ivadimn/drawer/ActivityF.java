package ru.ivadimn.drawer;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityF extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);

        toolbar = (Toolbar) findViewById(R.id.toolbar_f_id);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_f);
        //без toggle не появиться гамбургер
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);

        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator_f);
        navigation = (NavigationView) findViewById(R.id.navigation_f);
        navigation.setNavigationItemSelectedListener(this);
    }

    //обязательные для вызова
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_two, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.menu_item_add_id:
                menuItemSelect("R.id.menu_item_add_id");
                break;
            case R.id.menu_item_edit_id:
                menuItemSelect("R.id.menu_item_edit_id");
                break;
            case R.id.menu_item_del_id:
                menuItemSelect("R.id.menu_item_del_id");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

       Snackbar snackbar =  Snackbar.make(coordinator, "Вы брали из навигационного меню - " + item.getTitle(), Snackbar.LENGTH_SHORT);
       snackbar.setAction("Action", null);
       snackbar.show();
       drawerLayout.closeDrawer(GravityCompat.START);
       showFragment(new FragmentTwo());
       return true;
    }

    private void menuItemSelect(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame_f, fragment);
        ft.commit();
    }
}
