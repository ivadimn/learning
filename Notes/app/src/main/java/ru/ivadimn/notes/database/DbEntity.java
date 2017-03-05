package ru.ivadimn.notes.database;

/**
 * Created by vadim on 05.03.2017.
 */

public abstract class DbEntity implements Row {
    public static final int TYPE_NULL = 0;
    public static final int TYPE_INTEGER = 1;
    public static final int TYPE_LONG = 2;
    public static final int TYPE_REAL = 3;
    public static final int TYPE_STRING = 4;
    public static final int TYPE_BLOB = 5;

    protected int _id;
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public abstract DbShema getShema();
}
