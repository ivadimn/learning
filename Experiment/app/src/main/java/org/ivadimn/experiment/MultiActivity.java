package org.ivadimn.experiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MultiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    List<Upshot> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        recyclerView = (RecyclerView) findViewById(R.id.multi_list_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initUpshot();
        MultiAdapter ma = new MultiAdapter(list);
        recyclerView.setAdapter(ma);
    }

    private void initUpshot() {
        for(int i = 0; i < 10; i ++) {
            list.add(new Upshot(i + " Элемент", (i%2 > 0 ? true : false)));
        }
    }
}
