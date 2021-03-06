package ru.ivadimn.notescompanion.fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.adapters.ContactsAdapter;
import ru.ivadimn.notescompanion.listeners.LIClickListener;
import ru.ivadimn.notescompanion.model.Person;

/**
 * Created by vadim on 03.05.17.
 */

public class ContactsFragment extends PagerFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int  loaderId = 1;
    private List<Person> persons;
    private RecyclerView rv;
    private ContactsAdapter adapter;
    private LIClickListener listener;

    private ProgressDialog loadDlg;

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        persons = new ArrayList<>();
        adapter = new ContactsAdapter(persons);
        getActivity().getSupportLoaderManager().initLoader(loaderId, null, this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);

        rv = (RecyclerView) view.findViewById(R.id.contacts_rvlist_id);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        listener = new LIClickListener(getActivity(), rv, new LIClickListener.ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked on " + position + " position", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked on " + position + " position", Toast.LENGTH_LONG).show();

            }
        });
        rv.addOnItemTouchListener(listener );

        return view;
    }

    public void updateData() {


        adapter.updateData(persons);
    }

    public void loadImages() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {Person._ID, Person.DISPLAY_NAME, Person.HAS_PHONE_NUMBER };
        Loader<Cursor> loader = new CursorLoader(getContext(), Person.CONTACT_URI, projection,
                null, null, Person.DISPLAY_NAME);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Bitmap bmp;
        while (data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(Person._ID));
            String displayName =  data.getString(data.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int hasNumber = data.getInt(data.getColumnIndex(Person.HAS_PHONE_NUMBER));

            InputStream in = ContactsContract.Contacts.openContactPhotoInputStream(getActivity().getContentResolver(),
                    Uri.withAppendedPath(Person.CONTACT_URI, String.valueOf(id)));

            if (in != null)
                bmp = BitmapFactory.decodeStream(in);
            else
                bmp = null;

            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Person p = new Person(id, displayName, bmp);

            if (hasNumber != 0)
                p.setPhones(getPhones(id));
            persons.add(p);
        }
        adapter.updateData(persons);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        persons = null;
    }

    private List<String> getPhones(int id) {
        List<String> ps = new ArrayList<>();
        String[] projection = {Person.PHONE_CONTACT_ID, Person.NUMBER};
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(Person.NUMBER_URI, projection, Person.PHONE_CONTACT_ID + " = ?",
                new String[] {String.valueOf(id)}, Person.NUMBER);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ps.add(cursor.getString(cursor.getColumnIndex(Person.NUMBER)));
            }
        }
        return ps;
    }
}
