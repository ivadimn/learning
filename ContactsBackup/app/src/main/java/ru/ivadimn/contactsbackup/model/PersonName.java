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

    public PersonName() {
        super(MIME_TYPE);
    }

    public PersonName(String displayName, String givenName, String familyName) {
        super(MIME_TYPE);
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
    }

    private String displayName;
    private String givenName;
    private String familyName;

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

    @Override
    public String[] getStringValues() {
        return new String[] {displayName, givenName, familyName};
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
