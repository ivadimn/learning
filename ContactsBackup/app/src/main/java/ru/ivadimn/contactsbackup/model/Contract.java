package ru.ivadimn.contactsbackup.model;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by vadim on 01.08.2017.
 */

public class Contract {
    public static final class Raw {
        //таблица и поля
        public static final Uri CONTENT_URI = ContactsContract.RawContacts.CONTENT_URI;
        public static final String _ID = ContactsContract.RawContacts._ID;
        public static final String CONTACT_ID = ContactsContract.RawContacts.CONTACT_ID;
        public static final String ACCOUT_NAME = ContactsContract.RawContacts.ACCOUNT_NAME;
        public static final String ACCOUT_TYPE = ContactsContract.RawContacts.ACCOUNT_TYPE;
        public static final String CUSTOM_RINGTONE = ContactsContract.RawContacts.CUSTOM_RINGTONE;

        public static final String[] PROJECTION = {
                _ID, CONTACT_ID, ACCOUT_NAME, ACCOUT_TYPE, CUSTOM_RINGTONE};
    }

    public static final class Data {
        public static final Uri CONTENT_URI = ContactsContract.Data.CONTENT_URI;
        public static final String _ID = ContactsContract.Data._ID;     //это для изменения
        public static final String RAW_CONTACT_ID = ContactsContract.Contacts.Data.RAW_CONTACT_ID;
        public static final String MIME_TYPE = ContactsContract.Data.MIMETYPE;
        public static final String PHOTO_MIME_TYPE = ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE;
    }
}
