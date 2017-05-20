package ru.ivadimn.contactsbackup.model;

import android.net.Uri;
import android.provider.ContactsContract;

import java.net.URI;

/**
 * Created by vadim on 14.05.2017.
 */

public class RawContact {

    public static final int LOADER_ID = 1;
    //таблица и поля
    public static final Uri RAW_CONTACT_URI = ContactsContract.RawContacts.CONTENT_URI;
    public static final String _ID = ContactsContract.RawContacts._ID;
    public static final String CONTACT_ID = ContactsContract.RawContacts.CONTACT_ID;
    public static final String ACCOUT_NAME = ContactsContract.RawContacts.ACCOUNT_NAME;
    public static final String ACCOUT_TYPE = ContactsContract.RawContacts.ACCOUNT_TYPE;
    public static final String CUSTOM_RINGTONE = ContactsContract.RawContacts.CUSTOM_RINGTONE;

    public static final String[] PROJECTION = {
            _ID, CONTACT_ID, ACCOUT_NAME, ACCOUT_TYPE, CUSTOM_RINGTONE};

    private long _id;
    private long contactId;
    private String accountName;
    private String accountType;
    private String customRingtone;
    public RawContact(String accountName, String accountType) {
        this.accountName = accountName;
        this.accountType = accountType;
        data = new DataContact();
    }

    public RawContact(long _id, long contactId, String accountName, String accountType, String customRingtone) {
        this._id = _id;
        this.contactId = contactId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.customRingtone = customRingtone;
        data = new DataContact(contactId);
    }

    private DataContact data;

    public DataContact getData() {
        return data;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomRingtone() {
        return customRingtone;
    }

    public void setCustomRingtone(String customRingtone) {
        this.customRingtone = customRingtone;
    }
}
