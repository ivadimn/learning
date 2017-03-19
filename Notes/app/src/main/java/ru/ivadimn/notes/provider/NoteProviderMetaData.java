package ru.ivadimn.notes.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import ru.ivadimn.notes.model.Note;

/**
 * Created by vadim on 19.03.2017.
 */

public class NoteProviderMetaData {
    //URI
    static final String AUTHORITY = "ru.ivadimn.organizer";
    //path
    static final String NOTE_PATH = "notes";

    public static final Uri NOTE_CONTENT_URI =
            Uri.parse("content//" + AUTHORITY + "/" + NOTE_PATH);     //content://ru.ivadimn.organizer/notes
    //mime types
    public static final String NOTE_CONTENT_TYPE =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + NOTE_PATH;
    public static final String NOTE_CONTENT__ITEM_TYPE =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + NOTE_PATH;
    private NoteProviderMetaData () {}
    //внутренний класс для описания NoteTable
    public static final class NoteTableMetaData implements BaseColumns {
        private NoteTableMetaData() {}
        public static final String TABLE_NAME = "note";
        //определение URI и MIME типа


        public static final String TITLE = "title";
        public static final String NTEXT = "ntext";
        public static final String MOMENT = "moment";

        public static final String DEFAULT_SORT_ORDER = " moment DESC";

    }
}
