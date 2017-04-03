package ru.ivadimn.notescompanion.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import ru.ivadimn.notescompanion.adapters.NotesAdapter1;
import ru.ivadimn.notescompanion.interfaces.Listener;
import ru.ivadimn.notescompanion.interfaces.LongListener;
import ru.ivadimn.notescompanion.model.Note;
import ru.ivadimn.notescompanion.model.NoteProviderMetaData;

import static android.app.Activity.RESULT_OK;

/**
 * Created by vadim on 04.12.16.
 */

public class NoteFragment extends PagerFragment implements DlgFragment.DlgInterface,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static NoteFragment instance;

    private static final int DATA_ADDED = 1;
    private static final int DATA_CHANGED = 2;
    public static final String TAG = "NOTE_FRAGMENT";

    private RecyclerView rvListNotes;
    private NotesAdapter1 adapter;
    private App app;
    private FloatingActionButton fab;
    private Cursor cursor;


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
        adapter = new NotesAdapter1(getContext());
        adapter.setListener(listener);
        //adapter.setLongListener(longListener);
        rvListNotes.setAdapter(adapter);
        initUI(view);
        Log.d(TAG, "onCreateView list created");
        //получаем курсоп в другом потоке
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
        return view;
    }

    private void readNotes() {
        String[] projection = new String[]{
                NoteProviderMetaData.NoteTableMetaData._ID,
                NoteProviderMetaData.NoteTableMetaData.TITLE,
                NoteProviderMetaData.NoteTableMetaData.NTEXT,
                NoteProviderMetaData.NoteTableMetaData.MOMENT };

        Uri uri = NoteProviderMetaData.NOTE_CONTENT_URI;
        Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (c != null) {
            Log.d(TAG, "cursor not null");
        }

    }

    private void initUI(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
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
        cursor.moveToPosition(index);
        String title = cursor.getString(cursor.getColumnIndex(NoteProviderMetaData.NoteTableMetaData.TITLE));
        String ntext = cursor.getString(cursor.getColumnIndex(NoteProviderMetaData.NoteTableMetaData.NTEXT));
        Bundle bundle = new Bundle();
        DlgFragment dlg = DlgFragment.getDlgFragment(this);
        bundle.putInt(Note.INDEX, index);
        bundle.putString(Note.TITLE, title);
        bundle.putString(Note.TEXT, ntext);
        dlg.setArguments(bundle);
        dlg.show(getFragmentManager(), DlgFragment.TAG);
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
            adapter.notifyDataSetChanged();
        }
    };

    public LongListener longListener = new LongListener() {
        @Override
        public void onClick(int i) {

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
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onOkClick(int index, String title, String content) {
        ContentResolver cr = getActivity().getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put(NoteProviderMetaData.NoteTableMetaData.TITLE, title);
        cv.put(NoteProviderMetaData.NoteTableMetaData.NTEXT, content);
        cv.put(NoteProviderMetaData.NoteTableMetaData.MOMENT, System.currentTimeMillis());
        if (index == -1) {
            cr.insert(NoteProviderMetaData.NOTE_CONTENT_URI, cv);
        }
        else {
            cursor.moveToPosition(index);
            long id = cursor.getLong(cursor.getColumnIndex(NoteProviderMetaData.NoteTableMetaData._ID));
            Uri uri = NoteProviderMetaData.NOTE_CONTENT_URI;
            Uri udateUri = Uri.withAppendedPath(uri, Long.toString(id));
            cr.update(udateUri, cv, null, null);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelClick() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{
                NoteProviderMetaData.NoteTableMetaData._ID,
                NoteProviderMetaData.NoteTableMetaData.TITLE,
                NoteProviderMetaData.NoteTableMetaData.NTEXT,
                NoteProviderMetaData.NoteTableMetaData.MOMENT };
        return new CursorLoader(getContext(),
                NoteProviderMetaData.NOTE_CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursor = data;
        adapter.update(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
