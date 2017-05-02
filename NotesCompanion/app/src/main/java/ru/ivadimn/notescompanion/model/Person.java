package ru.ivadimn.notescompanion.model;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by vadim on 27.04.17.
 */

public class Person  {

    Uri CONTACT_URI = ContactsContract.Contacts.CONTENT_URI;
    public static final String _ID = ContactsContract.Contacts._ID;
    public static final String PHOTO_ID = ContactsContract.Contacts.PHOTO_ID;
    public static final String PHOTO_FILE_ID = ContactsContract.Contacts.PHOTO_FILE_ID;
    public static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    public static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    Uri NUMBER_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    public static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;


    private String name;
    private String[] phones;
    private int photo;

    public Person() {
        //no-op
    }

    public Person(String name, String phone, int photo) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
