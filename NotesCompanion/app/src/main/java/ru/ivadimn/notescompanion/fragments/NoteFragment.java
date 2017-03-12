package ru.ivadimn.notescompanion.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.ivadimn.notescompanion.App;
import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.activities.EditActivity;
import ru.ivadimn.notescompanion.adapters.NotesAdapter;
import ru.ivadimn.notescompanion.interfaces.Listener;
import ru.ivadimn.notescompanion.interfaces.LongListener;
import ru.ivadimn.notescompanion.model.Note;

import static android.app.Activity.RESULT_OK;

/**
 * Created by vadim on 04.12.16.
 */

public class NoteFragment extends PagerFragment implements DlgFragment.DlgInterface{

    private static NoteFragment instance;

    private static final int DATA_ADDED = 1;
    private static final int DATA_CHANGED = 2;
    public static final String TAG = "NOTE_FRAGMENT";

    private List<Note> notes;
    private RecyclerView rvListNotes;
    private NotesAdapter adapter;
    private App app;
    private FloatingActionButton fab;


    public static NoteFragment getInstance() {
        if (instance == null)
            instance = new NoteFragment();
        return instance;
    }

    public NoteFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = App.getInstance();
        notes = app.getNotes();
        Log.d(TAG, "onCreate notes created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_fragment, container, false);
        rvListNotes = (RecyclerView) view.findViewById(R.id.list_note_id);
        rvListNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListNotes.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 32;
            }
        });
        adapter = new NotesAdapter(notes);
        adapter.setListener(listener);
        adapter.setLongListener(longListener);
        rvListNotes.setAdapter(adapter);
        initUI(view);
        Log.d(TAG, "onCreateView list created");
        return view;
    }

    private void initUI(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!adapter.isDeleteMode())
                    addNote();
                else
                    deleteNotes();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        app.saveNotes(notes);
        Log.d(TAG, "onStop");
    }

    public void addNote() {
        Bundle bundle = new Bundle();
        DlgFragment dlg = DlgFragment.getDlgFragment(this);
        bundle.putInt(Note.INDEX, -1);
        dlg.setArguments(bundle);
        dlg.show(getFragmentManager(), DlgFragment.TAG);
    }

    public void editNote(int index) {
        Note note  = notes.get(index);
        Bundle bundle = new Bundle();
        DlgFragment dlg = DlgFragment.getDlgFragment(this);
        bundle.putInt(Note.INDEX, -1);
        bundle.putString(Note.TITLE, note.getTitle());
        bundle.putString(Note.TEXT, note.getContent());
        dlg.setArguments(bundle);
        dlg.show(getFragmentManager(), DlgFragment.TAG);
    }

    public void deleteNotes() {
        List<Note> temp = new ArrayList<>(notes);
        for (int i = 0; i < temp.size() ; i++) {
            Note n = temp.get(i);
            if (n.isReadyToDelete()) {
                notes.remove(n);
            }
        }
        adapter.notifyDataSetChanged();
        adapter.setDeleteMode(false);
        adapter.notifyItemRangeChanged(0, notes.size());
        fab.setImageResource(android.R.drawable.ic_input_add);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATA_ADDED && resultCode == RESULT_OK) {
            Note note = new Note(data.getStringExtra(Note.TITLE), data.getStringExtra(Note.TEXT));
            notes.add(note);
            adapter.notifyDataSetChanged();
        }
        else if (requestCode == DATA_CHANGED && resultCode == RESULT_OK) {
            int index = data.getIntExtra(Note.INDEX, -1);
            if (index < 0) return;
            Note note = notes.get(index);
            note.setTitle(data.getStringExtra(Note.TITLE));
            note.setContent(data.getStringExtra(Note.TEXT));
            note.setMoment(new Date());
            adapter.notifyDataSetChanged();
        }
    }

    public Listener listener = new Listener() {
        @Override
        public void onImageClick(View v, int i) {

        }

        @Override
        public void onTextClick(View v, int i) {
            click(v, i);
        }

        @Override
        public void onCardClick(View v, int i) {
            click(v, i);
        }

        @Override
        public void onCheckBoxClick(View v, int i) {
            Note note = notes.get(i);
            note.setReadyToDelete(!note.isReadyToDelete());
            adapter.notifyDataSetChanged();
        }
    };

    public LongListener longListener = new LongListener() {
        @Override
        public void onClick(int i) {
            adapter.setDeleteMode(!adapter.isDeleteMode());
            adapter.notifyItemRangeChanged(0, notes.size());
            if (adapter.isDeleteMode())
                fab.setImageResource(android.R.drawable.ic_input_delete);
            else
                fab.setImageResource(android.R.drawable.ic_input_add);
        }
    };

    private void click(View v, int i) {
        if (!adapter.isDeleteMode())
            editNote(i);
        else {
            Note note = notes.get(i);
            note.setReadyToDelete(!note.isReadyToDelete());
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onOkClick(int index, String title, String content) {
        Note note;
        if (index == -1) {
            note = new Note(title, content);
            notes.add(note);
        }
        else {
            note = notes.get(index);
            note.setTitle(title);
            note.setContent(content);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelClick() {

    }
}
