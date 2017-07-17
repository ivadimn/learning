package ru.ivadimn.contactsbackup.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    }

    public RawContact(long _id, long contactId, String accountName, String accountType, String customRingtone) {
        this._id = _id;
        this.contactId = contactId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.customRingtone = customRingtone;
        this.contactId = contactId;
    }

    private Bitmap photo;
    private byte[] photoBytes;


    private List<DataElement> elements = new ArrayList<>();

    public void addElement(DataElement element) {
        elements.add(element);
    }

    public List<DataElement> getElements(String mimeType) {
        List<DataElement> list = new ArrayList<>();
        for (DataElement e : elements) {
            if (e.getMimeType().equals(mimeType))
                list.add(e);
        }
        return list;
    }

    public long get_id() {
        return _id;
    }
    public long getContactId() {
        return contactId;
    }
    public String getAccountName() {
        return accountName;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getCustomRingtone() {
        return customRingtone;
    }

    public Bitmap getPhoto() {
        /*ByteBuffer  buffer = ByteBuffer.allocate(photo.getByteCount());
        photo.copyPixelsToBuffer(buffer);
        byte[] b = buffer.array();*/
        return photo;
    }
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }

}
