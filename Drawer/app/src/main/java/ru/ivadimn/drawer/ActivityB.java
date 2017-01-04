package ru.ivadimn.drawer;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityB extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private FloatingActionButton fab;
    private ImageView im;
    private String[] cats;
    private ListView lvCats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_id);
        mCollapsingToolbar.setTitle("Меню");
        ImageView im = (ImageView) findViewById(R.id.toolbar_image_id);
        im.setImageResource(R.drawable.menu);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_id);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar
                        .make(coordinatorLayout, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(
                                                ActivityB.this,
                                                R.string.snackbar_toast_action,
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                })
                        .show();
            }
        });
        /*cats = getResources().getStringArray(R.array.cats_array_ru);
        lvCats = (ListView) findViewById(R.id.left_drawer);
        lvCats.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, cats))*/


    }
}
