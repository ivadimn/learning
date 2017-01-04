package ru.ivadimn.drawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] cats;
    private ListView lvCats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cats = getResources().getStringArray(R.array.cats_array_ru);
        lvCats = (ListView) findViewById(R.id.left_drawer);

        lvCats.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, cats));
    }

}
