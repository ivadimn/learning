package ru.ivadimn.contactsbackup.model.data;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by vadim on 18.07.17.
 */

public class DataContract  {
    public static final Uri DATA_CONTACT_URI = ContactsContract.Data.CONTENT_URI;

    public static final String _ID = ContactsContract.Data._ID;     //это для изменения
    public static final String RAW_CONTACT_ID = ContactsContract.Contacts.Data.RAW_CONTACT_ID;
    public static final String MIME_TYPE = ContactsContract.Data.MIMETYPE;
    public static final String PHOTO_MIME_TYPE = ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE;
    //наименование столбцов photo
    public static final String PHOTO_ID = ContactsContract.CommonDataKinds.Photo.PHOTO;

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
}
