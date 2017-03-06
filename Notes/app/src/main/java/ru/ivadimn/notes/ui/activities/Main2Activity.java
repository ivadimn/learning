package ru.ivadimn.notes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.ivadimn.notes.App;
import ru.ivadimn.notes.R;
import ru.ivadimn.notes.database.DataManage;
import ru.ivadimn.notes.model.Note;
import ru.ivadimn.notes.model.Note1;
import ru.ivadimn.notes.ui.adapters.NotesAdapter1;
import ru.ivadimn.notes.ui.adapters.NotesAdapter2;


/**
 * здесь используется DbFlow
 */
public class Main2Activity extends AppCompatActivity implements AbsListView.MultiChoiceModeListener {

    public static final String TAG = "MAIN_ACTIVITY";
    private static final int DATA_ADDED = 1;
    private static final int DATA_CHANGED = 2;

    private List<Note1> notes;
    private ListView listView;
    private NotesAdapter2 adapter;
    private int checkedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        notes = new Select().from(Note1.class).queryList();
        initViews();
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.list_notes_id);

        adapter = new NotesAdapter2(this, notes);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        //registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mi_noteadd_id:
                addNote();
                break;
            case R.id.mi_clear_id:
                clearNotes();
                break;

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mi_noteedit_id:
                editNote(menuInfo.position);
                return true;
            case R.id.mi_notedelete_id:
                deleteNote(menuInfo.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void addNote() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, DATA_ADDED);
    }

    public void editNote(int index) {
        Note1 note = notes.get(index);
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Note.TITLE, note.getTitle());
        intent.putExtra(Note.TEXT, note.getContent());
        intent.putExtra(Note.INDEX, index);
        startActivityForResult(intent, DATA_CHANGED);
    }

    private void deleteNote(int index) {
        notes.remove(index);
        adapter.notifyDataSetChanged();
    }

    private void deleteNotes() {
        int chCount = listView.getCheckedItemCount();
        SparseBooleanArray spa = listView.getCheckedItemPositions();
        List<Note1> tmp = new ArrayList<>();
        for (int i = 0; i < spa.size(); i++) {
            if (spa.valueAt(i)) {
                tmp.add(notes.get(spa.keyAt(i)));
                notes.get(spa.keyAt(i)).delete();
            }
        }
        notes.removeAll(tmp);
        adapter.notifyDataSetChanged();
    }

    private void clearNotes() {
        for ( Note1 n : notes) {
            n.delete();
        }
        notes.clear();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATA_ADDED && resultCode == RESULT_OK) {
            Note1 note = new Note1(data.getStringExtra(Note.TITLE), data.getStringExtra(Note.TEXT));
            notes.add(note);
            note.save();
            adapter.notifyDataSetChanged();
        } else if (requestCode == DATA_CHANGED && resultCode == RESULT_OK) {
            int index = data.getIntExtra(Note.INDEX, -1);
            if (index < 0) return;
            Note1 note = notes.get(index);
            note.setTitle(data.getStringExtra(Note.TITLE));
            note.setContent(data.getStringExtra(Note.TEXT));
            note.setMoment(new Date());
            note.save();
            note.setChecked(false);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        clearChecked();
        //app.saveNotes(notes);

    }


    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long l, boolean b) {
        Log.d(TAG,  position + " is checked = " + b);
        notes.get(position).setChecked(b);
        Menu menu = actionMode.getMenu();
        if(listView.getCheckedItemCount() > 1)
            menu.setGroupVisible(R.id.menu_group_edit, false);
        else
            menu.setGroupVisible(R.id.menu_group_edit, true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Log.d(TAG,  " Create action mode ");
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Log.d(TAG,  " Prepare action mode ");
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
        Log.d(TAG,  " OnActionItemClicked ");
        switch (item.getItemId()) {
            case R.id.mi_noteedit_id:
                SparseBooleanArray spa = listView.getCheckedItemPositions();
                for (int i = 0; i < spa.size(); i++) {
                    if (spa.valueAt(i)) {
                        editNote(spa.keyAt(i));
                        listView.setItemChecked(spa.keyAt(i), false);
                        break;
                    }
                }
                break;
            case R.id.mi_notedelete_id:
                deleteNotes();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        actionMode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }

    private void clearChecked() {
        for (int i = 0; i < notes.size(); i++) {
            notes.get(i).setChecked(false);
        }
    }
}
