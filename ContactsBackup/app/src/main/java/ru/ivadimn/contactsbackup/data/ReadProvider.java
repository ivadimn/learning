package ru.ivadimn.contactsbackup.data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.DataElement;

/**
 * Created by vadim on 20.05.2017.
 */

public class ReadProvider {

    private Context context;
    private Uri uri;
    private Cursor data;


    public ReadProvider(Context context, Uri uri) {
        this.context = context;
        this.uri = uri;
    }

    public Cursor getCursor(String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {
        return context.getContentResolver()
                .query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public boolean initCursor(String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {
        data = context.getContentResolver()
                .query(uri, projection, selection, selectionArgs, sortOrder);
        return data != null;
    }

    public void closeCursor() {
        data.close();
    }

    public void getData(DataContact dataContact) {
        while(data.moveToNext()) {
            String itemType = data.getString(data.getColumnIndex(DataContact.MIME_TYPE));
            DataElement e = getDataElement(itemType);
            if (e != null)
                dataContact.addElement(e);
            else if (itemType.equals(DataContact.PHOTO_MIME_TYPE)) {
                byte[] photo = getPhoto();
                if (photo != null) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                    dataContact.setPhoto(bmp);
                    dataContact.setPhotoBytes(photo);
                }
            }
        }
    }

    private DataElement getDataElement(String itemType) {
        DataElement de = DataElement.createElement(itemType);
        if (de != null) {
            String fields[] = de.getFieldNames();
            for (int i = 0; i < fields.length; i++) {
                de.addValue(fields[i], data.getString(data.getColumnIndex(fields[i])));
            }
        }
        return de;
    }

    private byte[] getPhoto() {
        Bitmap bmp = null;
        byte[] photo = data.getBlob(data.getColumnIndex(DataContact.PHOTO_ID));
        return photo;
    }
}
