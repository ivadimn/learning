package ru.ivadimn.android0106.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0106.App;
import ru.ivadimn.android0106.R;
import ru.ivadimn.android0106.adapters.PersonAdapter;
import ru.ivadimn.android0106.model.Person;
import ru.ivadimn.android0106.model.Utils;

public class MainActivity extends AppCompatActivity {

    private static final int PERSON_ADD = 1;
    private static final int PERSON_VIEW = 2;

    private RecyclerView list;
    private List<Person> persons = new ArrayList<>();
    private PersonAdapter adapter;
    private FloatingActionButton fab;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.person_list_id);
        list.setLayoutManager(new LinearLayoutManager(this));

        persons = App.getInstance().loadPersons();

        adapter = new PersonAdapter(listener);
        adapter.updateData(persons);
        list.setAdapter(adapter);
        fab = (FloatingActionButton) findViewById(R.id.fab_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_back_id:
                undoDelete();
                break;
            case R.id.item_delete_id:
                deletePersons();
                break;
        }
        return true;
    }

    private PersonAdapter.PersonClickListener listener = new PersonAdapter.PersonClickListener() {
        @Override
        public void onClick(View view, int position) {
            handlePerson(position);
        }

        @Override
        public void onLongClick(View view, int position) {
            menu.setGroupVisible(R.id.group_id, true);
            adapter.setDeleteMode(true);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
    };

    private void handlePerson(int position) {
        Person p = persons.get(position);
        Intent intent = PersonActivity.createIntent(this, p, position);
        startActivityForResult(intent, PERSON_VIEW);
    }

    private void addPerson() {
        Intent intent = PersonActivity.createIntent(this, null, -1);
        startActivityForResult(intent, PERSON_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == PERSON_VIEW) {
            int position = data.getIntExtra(PersonActivity.INDEX, -1);
            Person p = PersonActivity.getPerson(data);
            persons.set(position, p);
            adapter.notifyDataSetChanged();
            return;
        }
        if (requestCode == PERSON_ADD) {
            Person p = PersonActivity.getPerson(data);
            persons.add(p);
            adapter.notifyDataSetChanged();
        }

    }

    private void undoDelete() {

        for (Person p : persons) {
            p.setDelete(false);
        }
        menu.setGroupVisible(R.id.group_id, false);
        adapter.setDeleteMode(false);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }

    private void deletePersons() {
        List<Person> temp = new ArrayList<>(persons);
        for (Person p : temp) {
            if (p.isDelete()) {
                persons.remove(p);
            }
        }
        menu.setGroupVisible(R.id.group_id, false);
        adapter.setDeleteMode(false);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.getInstance().savePersons(persons);
    }
}
