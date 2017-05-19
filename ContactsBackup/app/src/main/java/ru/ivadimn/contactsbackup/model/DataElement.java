package ru.ivadimn.contactsbackup.model;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by vadim on 19.05.2017.
 */

public abstract class DataElement {

    private HashMap<String, String> values = new HashMap<>();


    public HashMap<String, String> getValues() {
        return values;
    }

    public void addValue(String key, String val) {
        values.put(key, val);
    }

    public static DataElement createElement(String mimeType) {
        switch(mimeType) {
            case PersonName.MIME_TYPE:
                return new PersonName();
            case Phone.MIME_TYPE:
                return new Phone();
            case Email.MIME_TYPE:
                return new Email();
            default:
                return null;
        }
    }

    public List<String> getKeyList() {
        Set<String> keys = values.keySet();
        return new ArrayList<>(keys);
    }

    public String getValue(String key) {
        return values.get(key);
    }
    public abstract String getDescription(String key);

    public abstract String getMimeType();
    public abstract String[] getFieldNames();
}
