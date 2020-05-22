package ru.ivadimn.contactsbackup.model.data;

import android.provider.ContactsContract;

import ru.ivadimn.contactsbackup.App;
import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataElement;

/**
 * Created by vadim on 19.05.2017.
 */

public class PersonName extends DataElement {

    public static final String MIME_TYPE = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;
    //наименнгование стольбцов имени
    public static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;
    public static final String GIVEN_NAME = ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME;
    public static final String FAMILY_NAME = ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME;
    /**
     * The contact's honorific prefix, e.g. "Sir"
     * <P>Type: TEXT</P>
     */
    public static final String PREFIX = ContactsContract.CommonDataKinds.StructuredName.PREFIX;

    /**
     * The contact's middle name
     * <P>Type: TEXT</P>
     */
    public static final String MIDDLE_NAME = ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME;

    /**
     * The contact's honorific suffix, e.g. "Jr"
     */
    public static final String SUFFIX = ContactsContract.CommonDataKinds.StructuredName.SUFFIX;

    /**
     * The phonetic version of the given name for the contact.
     * <P>Type: TEXT</P>
     */
    public static final String PHONETIC_GIVEN_NAME = ContactsContract.CommonDataKinds.StructuredName.PHONETIC_GIVEN_NAME;

    /**
     * The phonetic version of the additional name for the contact.
     * <P>Type: TEXT</P>
     */
    public static final String PHONETIC_MIDDLE_NAME = ContactsContract.CommonDataKinds.StructuredName.PHONETIC_MIDDLE_NAME;

    /**
     * The phonetic version of the family name for the contact.
     * <P>Type: TEXT</P>
     */
    public static final String PHONETIC_FAMILY_NAME = ContactsContract.CommonDataKinds.StructuredName.PHONETIC_FAMILY_NAME;

    /**
     * The style used for combining given/middle/family name into a full name.
     * See {@link ContactsContract.FullNameStyle}.
     */
    public static final String FULL_NAME_STYLE = ContactsContract.CommonDataKinds.StructuredName.FULL_NAME_STYLE;

    /**
     * The alphabet used for capturing the phonetic name.
     * See ContactsContract.PhoneticNameStyle.
     */
    public static final String PHONETIC_NAME_STYLE = ContactsContract.CommonDataKinds.StructuredName.PHONETIC_NAME_STYLE;


    public static final String[] FIELDS = {DISPLAY_NAME, GIVEN_NAME, FAMILY_NAME, PREFIX, MIDDLE_NAME, SUFFIX,
            PHONETIC_GIVEN_NAME, PHONETIC_MIDDLE_NAME, FULL_NAME_STYLE, PHONETIC_NAME_STYLE};

    public PersonName() {
        super(MIME_TYPE);
    }


    //////определение абстрактных методов
    @Override
    public String getDescription(String key) {
        return key;
    }
    @Override
    public String[] getFieldNames() {
        return FIELDS;
    }



}
