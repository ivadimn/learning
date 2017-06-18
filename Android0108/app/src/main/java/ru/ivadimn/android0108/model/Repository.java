package ru.ivadimn.android0108.model;

import java.util.List;


import ru.ivadimn.android0108.App;

/**
 * Created by vadim on 15.06.17.
 */

public class Repository {

    public static final String DRAWERS = "DRAWERS";
    public static final String PLANETS = "PLANETS";

    private static List<ObjectInfo> objects;

    public static List<ObjectInfo> getObjects(String type) {
        if (objects == null) {
            objects = App.getInctance().getObjects(DRAWERS);
        }
        return objects;
    }

    public static ObjectInfo getObject(int index) {
        if (objects == null) return null;
        else
            return (index > -1 && index < objects.size()) ? objects.get(index) : null;
    }

    public static void resetObjects() {
        objects = null;
    }


}
