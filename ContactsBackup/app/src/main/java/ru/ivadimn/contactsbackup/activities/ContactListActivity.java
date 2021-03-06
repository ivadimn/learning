package ru.ivadimn.contactsbackup.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
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
import ru.ivadimn.contactsbackup.data.ReadProvider;
import ru.ivadimn.contactsbackup.listeners.RVItemListener;
import ru.ivadimn.contactsbackup.model.Contract;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.DataElement;
import ru.ivadimn.contactsbackup.model.DataElementDisplay;
import ru.ivadimn.contactsbackup.model.Email;
import ru.ivadimn.contactsbackup.model.data.DataContract;
import ru.ivadimn.contactsbackup.model.data.PersonName;
import ru.ivadimn.contactsbackup.model.Phone;
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
        Loader<Cursor> loader = new CursorLoader(getBaseContext(), Contract.Raw.CONTENT_URI,
                Contract.Raw.PROJECTION, null, null, Contract.Raw.CONTACT_ID);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ReadProvider read = new ReadProvider(this, DataContract.DATA_CONTACT_URI);
        while(data.moveToNext()) {
            int _id = data.getInt(data.getColumnIndex(Contract.Raw._ID));
            int contactId = data.getInt(data.getColumnIndex(Contract.Raw.CONTACT_ID));
            String accountName = data.getString(data.getColumnIndex(Contract.Raw.ACCOUT_NAME));
            String accountType = data.getString(data.getColumnIndex(Contract.Raw.ACCOUT_TYPE));
            String customRingtone = data.getString(data.getColumnIndex(Contract.Raw.CUSTOM_RINGTONE));
            RawContact rc = new RawContact(_id, contactId, accountName, accountType, customRingtone);
            read.initCursor(null, DataContract.RAW_CONTACT_ID + " = ?", new String[] {String.valueOf(contactId)}, null);
            //read.getData(rc.getData());
            read.readData(rc);
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
            RawContact rw = rawContacts.get(position);
            Intent intent = new Intent(context, ContactDetailActivity.class);
            //String n = rw.getData().getName().getDisplayName();
            //intent.putExtra(PersonName.MIME_TYPE, n);
            //byte[] photo = rw.getData().getPhotoBytes();
            //intent.putExtra(DataContact.PHOTO_MIME_TYPE, photo);
            ArrayList<DataElementDisplay> de = getDisplayElements(rw);
            intent.putParcelableArrayListExtra(DataElementDisplay.LIST_ELEMENT, de);
            startActivity(intent);
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    };

    private ArrayList<DataElementDisplay> getDisplayElements(RawContact rw) {
        ArrayList<DataElementDisplay> list = new ArrayList<>();
        List<DataElement> ps = rw.getElementsByType(Phone.MIME_TYPE);
        for (DataElement p : ps) {
            list.add(new DataElementDisplay(Phone.MIME_TYPE, (String[]) p.getValues().toArray()));
        }
        return list;
    }

}
