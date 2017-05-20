package ru.ivadimn.contactsbackup.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 14.05.2017.
 */

public class DataContact {

    public static final Uri DATA_CONTACT_URI = ContactsContract.Data.CONTENT_URI;

    public static final String _ID = ContactsContract.Data._ID;
    public static final String CONTACT_ID = ContactsContract.Data.CONTACT_ID;
    public static final String MIME_TYPE = ContactsContract.Data.MIMETYPE;
    public static final String PHOTO_MIME_TYPE = ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE;

    //эти типы данных на развитие
    public static final String ORGANIZATION_TYPE = ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE;
    public static final String IM_TYPE = ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE;
    public static final String NICK_NAME_TYPE = ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE;
    public static final String NOTE_TYPE = ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE;
    public static final String STRUCT_POSTAL_TYPE = ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE;
    public static final String GROUP_TYPE = ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE;
    public static final String WEBSITE_TYPE = ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE;
    public static final String EVENT_TYPE = ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE;
    public static final String RELATION_TYPE = ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE;
    public static final String SIP_ADDRESS_TYPE = ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE;

    public DataContact() {
        //no-op
    }

    public DataContact(long contactId) {
        this.contactId = contactId;
    }

    //наименование столбцов photo
    public static final String PHOTO_ID = ContactsContract.CommonDataKinds.Photo.PHOTO;
    public static final String PHOTO_FILE_ID = ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID;

    private List<DataElement> dataList = new ArrayList<>();

    public void addElement(DataElement e) {
        dataList.add(e);
    }
    public List<DataElement> getDataElements() {
        return dataList;
    }

    private long contactId;
    private Bitmap photo;
    private byte[] photoBytes;

    public Bitmap getPhoto() {
        /*ByteBuffer  buffer = ByteBuffer.allocate(photo.getByteCount());
        photo.copyPixelsToBuffer(buffer);
        byte[] b = buffer.array();*/
        return photo;
    }

    public String getName() {
        for (DataElement de : dataList) {
            if (de.getMimeType() == PersonName.MIME_TYPE )
                return de.getValue(PersonName.DISPLAY_NAME);
        }
        return null;
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

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}
