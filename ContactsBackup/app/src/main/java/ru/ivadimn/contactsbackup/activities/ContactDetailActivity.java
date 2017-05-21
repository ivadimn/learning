package ru.ivadimn.contactsbackup.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.adapters.ContactDetailAdapter;
import ru.ivadimn.contactsbackup.adapters.RawContactsAdapter;
import ru.ivadimn.contactsbackup.listeners.RVItemListener;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.DataElementDisplay;
import ru.ivadimn.contactsbackup.model.PersonName;
import ru.ivadimn.contactsbackup.model.RawContact;

public class ContactDetailActivity extends AppCompatActivity {

    private ArrayList<DataElementDisplay> elements;
    private RecyclerView recyclerView;
    private ContactDetailAdapter adapter;
    private Context context;

    private TextView tvName;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        initUI();
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra(PersonName.MIME_TYPE));
        byte[] photo = intent.getByteArrayExtra(DataContact.PHOTO_MIME_TYPE);
        if (photo != null) {
            imgPhoto.setImageBitmap(BitmapFactory.decodeByteArray(photo, 0, photo.length));
        }
        else {
            imgPhoto.setImageResource(R.drawable.ic_mood_black_24dp);
        }
        elements = intent.getParcelableArrayListExtra(DataElementDisplay.LIST_ELEMENT);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactDetailAdapter(elements);
        recyclerView.setAdapter(adapter);
    }

    public void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_elementlist_id);
        tvName = (TextView) findViewById(R.id.tv_displayname_id);
        imgPhoto = (ImageView) findViewById(R.id.img_photo_id);
    }
}
