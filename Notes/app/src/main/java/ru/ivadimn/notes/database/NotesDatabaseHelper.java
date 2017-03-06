package ru.ivadimn.notes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * немного не доделанный класс
 * Created by vadim on 05.03.2017.
 */

public class NotesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "organizer";
    private static final int DB_VERSION = 1;

    public NotesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE NOTE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TITLE TEXT, "
                    + "NTEXT TEXT);");
        }
       /*if (oldVersion < 2) {
            db.execSQL("ALTER TABLE NOTE ADD COLUMN MOMENT NUMERIC;");
        }*/
    }
}
