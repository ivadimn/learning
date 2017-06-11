package ru.ivadimn.android0106;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0106.adapters.PersonAdapter;
import ru.ivadimn.android0106.model.Person;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private List<Person> persons = new ArrayList<>();
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.person_list_id);
        list.setLayoutManager(new LinearLayoutManager(this));
        initData();
        adapter = new PersonAdapter();
        adapter.updateData(persons);
        list.setAdapter(adapter);

    }

    private void initData() {
        persons.add(new Person("vadim ivanov", "+79116789054", "aaaa@mail.com", "GFJ", "1111, 11111"));
        persons.add(new Person("peter sidoprov", "+79566789054", "bbb@mail.com", "GFJ", "1111, 11111"));
    }
}
