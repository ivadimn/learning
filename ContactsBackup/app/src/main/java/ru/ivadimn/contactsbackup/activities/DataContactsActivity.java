package ru.ivadimn.contactsbackup.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.Element;

public class DataContactsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "DATA_CONTACT_ACTIVITY";

    private TextView tvData;
    private ImageView image;
    private long contactId;
    private DataContact dataList = new DataContact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        contactId = intent.getLongExtra(DataContact.CONTACT_ID, 0);
        setContentView(R.layout.activity_data_contacts);
        tvData = (TextView) findViewById(R.id.tv_data_id);
        image  = (ImageView) findViewById(R.id.img_photo_id);

        getSupportLoaderManager().initLoader(2, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = new CursorLoader(this, DataContact.DATA_CONTACT_URI, null,
                DataContact.CONTACT_ID + " = ?", new String[] {String.valueOf(contactId)}, null);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        StringBuilder sb = new StringBuilder();
        Log.d(TAG, "Загрузка данных завершена");
        sb.append("Типы данных: \n");
        while(data.moveToNext()) {
            String itemType = data.getString(data.getColumnIndex(DataContact.MIME_TYPE));
            Element e = getDataElement(data, itemType);
            if (e != null)
                dataList.addElement(e);
        }

        sb.append("Фото файл : " + dataList.getPhotoFile());
        Bitmap bmp = dataList.getPhoto();
        if (bmp != null)
            image.setImageBitmap(bmp);
        else
            image.setImageResource(android.R.drawable.picture_frame);
        List<Element> list = dataList.getDataList();
        for (Element e : list) {
            List<String> fs = e.getKeyList();
            for (String s : fs) {
                sb.append(s + " : " + e.getValue(s) + "\n");
            }
        }
        Log.d(TAG, "Типы данных получены");
        tvData.setText(sb.toString());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public Element getDataElement(Cursor data, String itemType) {

        switch(itemType) {
            case DataContact.STRUCT_NAME_TYPE:
                return getElements(data, itemType, DataContact.getStructNameFields());
            case DataContact.PHONE_TYPE:
                return getElements(data, itemType, DataContact.getPhoneFields());
            case DataContact.EMAIL_TYPE:
                return getElements(data, itemType, DataContact.getEmailFields());
            case DataContact.PHOTO_TYPE:
                getPhoto(data, itemType);
                return null;
            default:
                return null;
        }
    }

    public Element getElements(Cursor data, String itemType, String[] fileds) {
        Element e = new Element(itemType);
        for (int i = 0; i < fileds.length; i++) {
            e.addElement(fileds[i], data.getString(data.getColumnIndex(fileds[i])));
        }
        return e;
    }

    public Bitmap getPhoto(Cursor data, String itemType) {
        Bitmap bmp = null;
        String fname = data.getString(data.getColumnIndex(DataContact.PHOTO_FILE_ID));
        byte[] photo = data.getBlob(data.getColumnIndex(DataContact.PHOTO_ID));
        if (fname != null)
            dataList.setPhotoFile(fname);
        if (photo != null) {
            bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            dataList.setPhoto(bmp);
        }
        return bmp;
    }
}
