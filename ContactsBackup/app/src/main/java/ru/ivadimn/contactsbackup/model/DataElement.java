package ru.ivadimn.contactsbackup.model;

import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ru.ivadimn.contactsbackup.model.data.PersonName;

/**
 * Created by vadim on 19.05.2017.
 */

public abstract class DataElement {

    private String mimeType;

    protected DataElement(String mimeType) {
        this.mimeType = mimeType;
    }
    private HashMap<String, String> values = new HashMap<>();

    public void addValue(String key, String val) {
        values.put(key, val);
    }
    public List<String> getKeyList() {
        Set<String> keys = values.keySet();
        return new ArrayList<>(keys);
    }

    public void setTableValues(HashMap<String, String> values) {
        this.values = values;
    }
    public HashMap<String, String> getTableValues() {
        return values;
    }

    public String getValue(String key) {
        return values.get(key);
    }

    public List<String> getValues() {
        return new ArrayList<>(values.values());
    }

    public void setValue(String key, String value) {
        if (values.containsKey(key)) {
            values.replace(key, value);
        }
        else
            values.put(key, value);

    }

    public String getMimeType() {
        return mimeType;
    }

    public abstract String getDescription(String key);
    public abstract String[] getFieldNames();


    public static DataElement createElement(String mimeType) {
        switch(mimeType) {
            case PersonName.MIME_TYPE:
                return new PersonName();
            default:
                return null;
        }
    }

}
