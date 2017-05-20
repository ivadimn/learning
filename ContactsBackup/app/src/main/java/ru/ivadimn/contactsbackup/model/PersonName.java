package ru.ivadimn.contactsbackup.model;

import android.provider.ContactsContract;

/**
 * Created by vadim on 19.05.2017.
 */

public class PersonName extends DataElement {

    public static final String MIME_TYPE = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;
    //наименнгование стольбцов имени
    public static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;
    public static final String GIVEN_NAME = ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME;
    public static final String FAMILY_NAME = ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME;


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
        return new String[] {DISPLAY_NAME, GIVEN_NAME, FAMILY_NAME};
    }
}
