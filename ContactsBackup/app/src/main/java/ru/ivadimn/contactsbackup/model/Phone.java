package ru.ivadimn.contactsbackup.model;

import android.provider.ContactsContract;

import java.util.HashMap;

/**
 * Created by vadim on 19.05.2017.
 */

public class Phone  extends DataElement {
    public static final String MIME_TYPE = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
    //наименование столбцов телефона
    public static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    public static final String LABEL = ContactsContract.CommonDataKinds.Phone.LABEL;
    public static final String TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;

    private String number;
    private String label;
    private String type;

    @Override
    public String getMimeType() {
        return MIME_TYPE;
    }
    @Override
    public String[] getFieldNames() {
        return new String[] {NUMBER, TYPE, LABEL};
    }

}
