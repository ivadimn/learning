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

    public static final String STRUCT_NAME_TYPE = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;
    public static final String PHONE_TYPE = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
    public static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
    public static final String PHOTO_TYPE = ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE;
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

    //наименнгование стольбцов имени
    public static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;
    public static final String GIVEN_NAME = ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME;
    public static final String FAMILY_NAME = ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME;
    //наименование столбцов телефона
    public static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    public static final String LABEL = ContactsContract.CommonDataKinds.Phone.LABEL;
    public static final String TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
    //наименование столбцов email
    public static final String ADDRESS = ContactsContract.CommonDataKinds.Email.ADDRESS;
    public static final String EMAIL_NAME = ContactsContract.CommonDataKinds.Email.DISPLAY_NAME;
    //наименование столбцов photo
    public static final String PHOTO_ID = ContactsContract.CommonDataKinds.Photo.PHOTO;
    public static final String PHOTO_FILE_ID = ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID;

    private List<Element> dataList = new ArrayList<>();

    public void addElement(Element e) {
        dataList.add(e);
    }

    public static String[] getStructNameFields() {
        return new String[] {DISPLAY_NAME, GIVEN_NAME, FAMILY_NAME};
    }
    public static String[] getPhoneFields() {
        return new String[] {NUMBER, TYPE, LABEL};
    }
    public static String[] getEmailFields() {
        return new String[] {ADDRESS, EMAIL_NAME};
    }

    public List<Element> getDataList() {
        return dataList;
    }

    private Bitmap photo;
    private String photoFile;

    public Bitmap getPhoto() {
        /*ByteBuffer  buffer = ByteBuffer.allocate(photo.getByteCount());
        photo.copyPixelsToBuffer(buffer);
        byte[] b = buffer.array();*/
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }
}
