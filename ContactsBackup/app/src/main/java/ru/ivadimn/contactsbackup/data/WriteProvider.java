package ru.ivadimn.contactsbackup.data;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.DataElement;
import ru.ivadimn.contactsbackup.model.Email;
import ru.ivadimn.contactsbackup.model.PersonName;
import ru.ivadimn.contactsbackup.model.Phone;
import ru.ivadimn.contactsbackup.model.RawContact;

/**
 * Created by vadim on 21.05.2017.
 */

public class WriteProvider  {

    public static final String TAG = "WRITE_PROVIDER";
    private Context context;

    public WriteProvider(Context context) {
        this.context = context;
    }

    public void wd() {
        ArrayList<ContentProviderOperation> op = new ArrayList<ContentProviderOperation>();

/* Добавляем пустой контакт */
        op.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
/* Добавляем данные имени */
        op.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Robert Smith")
                .build());
/* Добавляем данные телефона */
        op.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, "11-22-33")
                .withValue(Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, op);
        } catch (Exception e) {
            String msg = e.getMessage();
            Log.d("Exception: ", e.getMessage());
        }
    }

    public void writeData(RawContact rw) {
        ArrayList<ContentProviderOperation> op = new ArrayList<>();
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(RawContact.RAW_CONTACT_URI);
        builder.withValue(RawContact.ACCOUT_NAME, rw.getAccountName());
        builder.withValue(RawContact.ACCOUT_TYPE, rw.getAccountType());
        op.add(builder.build());
        prepareName(op, rw.getData().getName());
        preparePhones(op, rw.getData().getPhones());
        prepareEmails(op, rw.getData().getEmails());
        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, op);
            Log.d(TAG, "Insert complete");
        } catch (Exception e) {
            String msg = e.getMessage();
            Log.d(TAG, e.getMessage());
        }
    }

    private void prepareName(ArrayList<ContentProviderOperation> op,
                             PersonName pname) {
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(DataContact.DATA_CONTACT_URI);
        builder.withValueBackReference(DataContact.CONTACT_ID, 0);
        builder.withValue(DataContact.MIME_TYPE, PersonName.MIME_TYPE);
        builder.withValue(PersonName.DISPLAY_NAME, pname.getDisplayName());
        builder.withValue(PersonName.GIVEN_NAME, pname.getGivenName());
        builder.withValue(PersonName.FAMILY_NAME, pname.getFamilyName());
        op.add(builder.build());
    }

    private void preparePhones(ArrayList<ContentProviderOperation> op,
                              List<Phone> phones) {
        ContentProviderOperation.Builder builder;
        for (Phone p : phones) {
            builder = ContentProviderOperation.newInsert(DataContact.DATA_CONTACT_URI);
            builder.withValueBackReference(DataContact.CONTACT_ID, 0);
            builder.withValue(DataContact.MIME_TYPE, Phone.MIME_TYPE);
            builder.withValue(Phone.NUMBER, p.getNumber());
            builder.withValue(Phone.TYPE, p.getType());
            builder.withValue(Phone.LABEL, p.getLabel());
            op.add(builder.build());
        }
    }

    private void prepareEmails(ArrayList<ContentProviderOperation> op,
                              List<Email> emails) {
        ContentProviderOperation.Builder builder;
        for (Email e : emails) {
            builder = ContentProviderOperation.newInsert(DataContact.DATA_CONTACT_URI);
            builder.withValueBackReference(DataContact.CONTACT_ID, 0);
            builder.withValue(DataContact.MIME_TYPE, Email.MIME_TYPE);
            builder.withValue(Email.ADDRESS, e.getAddress());
            builder.withValue(Email.EMAIL_NAME, e.getEmailName());
            op.add(builder.build());
        }
    }
}
