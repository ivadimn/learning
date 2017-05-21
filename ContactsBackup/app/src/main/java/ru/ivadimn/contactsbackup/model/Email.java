package ru.ivadimn.contactsbackup.model;

import android.provider.ContactsContract;

/**
 * Created by vadim on 19.05.2017.
 */

public class Email extends DataElement {

    public static final String MIME_TYPE = ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
    //наименование столбцов email
    public static final String ADDRESS = ContactsContract.CommonDataKinds.Email.ADDRESS;
    public static final String EMAIL_NAME = ContactsContract.CommonDataKinds.Email.DISPLAY_NAME;

    public Email() {
        super(MIME_TYPE);
    }

    public Email(String address, String emailName) {
        super(MIME_TYPE);
        this.address = address;
        this.emailName = emailName;
    }

    private String address;
    private String emailName;


    @Override
    public String getDescription(String key) {
        return null;
    }

    @Override
    public String getMimeType() {
        return MIME_TYPE;
    }
    @Override
    public String[] getFieldNames() {
        return new String[] {ADDRESS, EMAIL_NAME};
    }

    @Override
    public String[] getStringValues() {
        return new String[] {address, emailName};
    }
}
