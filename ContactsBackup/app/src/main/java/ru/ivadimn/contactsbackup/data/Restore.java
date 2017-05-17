package ru.ivadimn.contactsbackup.data;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.Element;
import ru.ivadimn.contactsbackup.model.RawContact;

/**
 * Created by vadim on 17.05.2017.
 */

public class Restore {

    public static final String TAG = "RESTORE";
    private Context context;

    //
    public Restore(Context context) {
        this.context = context;
    }

    //это надо в отдельном потоке сделать
    public boolean insertContact(DataContact data) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> op = new ArrayList<>();
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(RawContact.RAW_CONTACT_URI);
        builder.withValue(RawContact.ACCOUT_NAME, null);
        builder.withValue(RawContact.ACCOUT_TYPE, null);
        op.add(builder.build());
        List<Element> dataList = data.getDataList();
        for (Element e : dataList) {
            List<String> keys = e.getKeyList();
            for (String key : keys) {
                String val = e.getValue(key);
                builder = ContentProviderOperation.newInsert(DataContact.DATA_CONTACT_URI);
                builder.withValueBackReference(DataContact.CONTACT_ID, 0);
                builder.withValue(e.getMime(), val);
                op.add(builder.build());
            }
        }
        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, op);
            Log.d(TAG, "Insert complete");
            return true;
        } catch (RemoteException e) {
            Log.d(TAG, e.getMessage());
        } catch (OperationApplicationException e) {
            Log.d(TAG, e.getMessage());
        }
        return false;
    }
}
