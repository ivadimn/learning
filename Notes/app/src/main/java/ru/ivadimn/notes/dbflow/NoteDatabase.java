package ru.ivadimn.notes.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vadim on 05.03.2017.
 */
@Database(name = NoteDatabase.DB_NAME, version = NoteDatabase.DB_VERSION)
public class NoteDatabase {
    public static final String DB_NAME = "notesbase";
    public static final int DB_VERSION = 1;

}
