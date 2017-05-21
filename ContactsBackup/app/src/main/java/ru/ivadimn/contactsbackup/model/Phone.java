package ru.ivadimn.contactsbackup.model;

import android.provider.ContactsContract;

import java.util.HashMap;

import ru.ivadimn.contactsbackup.App;

/**
 * Created by vadim on 19.05.2017.
 */

public class Phone  extends DataElement {

    public static final String MIME_TYPE = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
    //наименование столбцов телефона
    public static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    public static final String LABEL = ContactsContract.CommonDataKinds.Phone.LABEL;
    public static final String TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;

    public Phone() {
        super(MIME_TYPE);
    }
    public Phone(String number, String label, int type) {
        super(MIME_TYPE);
        this.number = number;
        this.label = label;
        this.type = type;
    }

    private String number;
    private String label;
    private int type;

    public String getStringType() {
        int resId = ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(type);
        return App.getInstance().getStringFromResource(resId);
    }

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
        return new String[] {NUMBER, TYPE, LABEL};
    }

    @Override
    public String[] getStringValues() {
        return new String[]
                {number,
                 String.valueOf(type),
                 label};
    }
}
