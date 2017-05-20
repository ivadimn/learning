package ru.ivadimn.contactsbackup.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.adapters.ContactsAdapter;
import ru.ivadimn.contactsbackup.adapters.RawContactsAdapter;
import ru.ivadimn.contactsbackup.data.ReadProvider;
import ru.ivadimn.contactsbackup.listeners.RVItemListener;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.RawContact;

public class ContactListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private List<RawContact> rawContacts = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        context = getBaseContext();
        getSupportLoaderManager().initLoader(RawContact.LOADER_ID, null, this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_contact_list_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RVItemListener(context, recyclerView, listener));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = new CursorLoader(getBaseContext(), RawContact.RAW_CONTACT_URI,
                RawContact.PROJECTION, null, null, RawContact.CONTACT_ID);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ReadProvider read = new ReadProvider(this, DataContact.DATA_CONTACT_URI);
        while(data.moveToNext()) {
            int _id = data.getInt(data.getColumnIndex(RawContact._ID));
            int contactId = data.getInt(data.getColumnIndex(RawContact.CONTACT_ID));
            String accountName = data.getString(data.getColumnIndex(RawContact.ACCOUT_NAME));
            String accountType = data.getString(data.getColumnIndex(RawContact.ACCOUT_TYPE));
            String customRingtone = data.getString(data.getColumnIndex(RawContact.CUSTOM_RINGTONE));
            RawContact rc = new RawContact(_id, contactId, accountName, accountType, customRingtone);
            read.initCursor(null, DataContact.CONTACT_ID + " = ?", new String[] {String.valueOf(contactId)}, null);
            read.getData(rc.getData());
            read.closeCursor();
            rawContacts.add(rc);
        }
        adapter.updateData(rawContacts);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private RVItemListener.OnRVItemClickListener listener = new RVItemListener.OnRVItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent(context, DataContactsActivity.class);
            intent.putExtra(DataContact.CONTACT_ID, rawContacts.get(position).getContactId());
            startActivity(intent);
        }
        @Override
        public void onLongClick(View view, int position) {

        }
    };
}
