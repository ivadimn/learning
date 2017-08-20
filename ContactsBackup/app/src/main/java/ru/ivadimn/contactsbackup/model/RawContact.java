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

    public List<DataElement> getElements() {
        return elements;
    }
    //получить элементы определённого типа (только телефоны или только emails)
    public List<DataElement> getElementsByType(String mimeType) {
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
