package ru.ivadimn.notescompanion.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by vadim on 27.04.17.
 */

public class Person  {

    public static final Uri CONTACT_URI = ContactsContract.Contacts.CONTENT_URI;
    public static final String _ID = ContactsContract.Contacts._ID;
    public static final String PHOTO_ID = ContactsContract.Contacts.PHOTO_ID;
    public static final String PHOTO_FILE_ID = ContactsContract.Contacts.PHOTO_FILE_ID;
    public static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    public static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    //номера телефонов
    public static final  Uri NUMBER_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    public static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    public static final String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;

    //email адреса
    public static final Uri EMAIL_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    public static final String EMAIL = ContactsContract.CommonDataKinds.Email.ADDRESS;
    public static final String EMAIL_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;


    private int id;
    private String name;
    private int hasPhoneNumber;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private Drawable image;

    private int photo;

    public Person() {
        //no-op
    }

    public Person(int id, String name, int hasPhoneNumber, InputStream in) {
        this.id = id;
        this.name = name;
        this.hasPhoneNumber = hasPhoneNumber;

        if (in != null) {
            image = Drawable.createFromStream(in, name);
        }
        else
            image = null;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
