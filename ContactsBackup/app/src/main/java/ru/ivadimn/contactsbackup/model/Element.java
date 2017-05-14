package ru.ivadimn.contactsbackup.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by vadim on 14.05.2017.
 */

public class Element {
    private String mime;
    private HashMap<String, String> data = new HashMap<>();

    public Element(String mime) {
        this.mime = mime;
    }

    public void addElement(String key, String value) {
        data.put(key, value);
    }

    public List<String> getKeyList() {
        Set<String> keys = data.keySet();
        return new ArrayList<>(keys);
    }

    public String getValue(String key) {
        return data.get(key);
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
