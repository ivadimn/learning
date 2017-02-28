package ru.ivadimn.notes.ui.activities;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.notes.App;
import ru.ivadimn.notes.R;
import ru.ivadimn.notes.model.Note;
import ru.ivadimn.notes.ui.adapters.NotesAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Note> notes;
    private RecyclerView rvListNotes;
    private NotesAdapter adapter;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App)getApplication();
        notes = app.getNotes();
        if (notes == null)
            notes = new ArrayList<>();
        initViews();

    }

    private void initViews() {
        rvListNotes = (RecyclerView) findViewById(R.id.list_notes_id);
        rvListNotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListNotes.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 32;
            }
        });
        adapter = new NotesAdapter(notes);
        rvListNotes.setAdapter(adapter);
        registerForContextMenu(rvListNotes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
