package ru.ivadimn.contactsbackup.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.DataElement;

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
            DataElement e = getDataElement(data, itemType);
            if (e != null)
                dataList.addElement(e);
        }


        Bitmap bmp = dataList.getPhoto();
        if (bmp != null)
            image.setImageBitmap(bmp);
        else
            image.setImageResource(android.R.drawable.picture_frame);
        List<DataElement> list = dataList.getDataElements();
        for (DataElement e : list) {
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

    public DataElement getDataElement(Cursor data, String itemType) {
        DataElement de = DataElement.createElement(itemType);
        if (de != null) {
            String fields[] = de.getFieldNames();
            for (int i = 0; i < fields.length; i++) {
                de.addValue(fields[i], data.getString(data.getColumnIndex(fields[i])));
            }
        }
        return de;
    }


    public Bitmap getPhoto(Cursor data, String itemType) {
        Bitmap bmp = null;
        byte[] photo = data.getBlob(data.getColumnIndex(DataContact.PHOTO_ID));
        if (photo != null) {
            bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            dataList.setPhoto(bmp);
        }
        return bmp;
    }
}
