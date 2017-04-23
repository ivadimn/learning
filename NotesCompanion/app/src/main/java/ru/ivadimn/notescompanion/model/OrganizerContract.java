package ru.ivadimn.notescompanion.model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

/**
 * Created by vadim on 09.04.2017.
 */


public final class OrganizerContract {

    public static final String AUTHORITY = "ru.ivadimn.organizer";
    public static final String NOTE_PATH = "notes";
    public static final String EVENT_PATH = "events";

    //общее URI
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY );
    /**
     * Константы для таблицы note провайдера
     */
    public static final class Notes implements CommonColumns {
        //URI для таблицы заметок
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(OrganizerContract.CONTENT_URI, NOTE_PATH);

        //mime type для каталога заметок
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + "." + NOTE_PATH;

        //mime type для одной заметки
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + "." + NOTE_PATH;


        public static final String TABLE_NAME = "note";
        //колонки таблицы
        public static final String TITLE = "title";
        public static final String NTEXT = "ntext";
        public static final String MOMENT = "moment";
        public static final String DEFAULT_SORT_ORDER = " moment ASC";

        public static final String[] PROJECTION_ALL = {
            _ID, TITLE, NTEXT, MOMENT};

        public static final HashMap<String, String> PROJECTION_MAP;
        static {
            PROJECTION_MAP = new HashMap<>();
            PROJECTION_MAP.put(_ID, _ID);
            PROJECTION_MAP.put(TITLE, TITLE);
            PROJECTION_MAP.put(NTEXT, NTEXT);
            PROJECTION_MAP.put(MOMENT, MOMENT);
        }
    }

    public static final class Events implements CommonColumns {
        //URI указаыающая на сптсок событий
        public static final Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + EVENT_PATH);

        //mime type для списка событий
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + "." + EVENT_PATH;

        //mime type для списка событий
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + "." + EVENT_PATH;

        public static final String TABLE_NAME = "event";
        //колонки таблицы
        public static final String MOMENT = "moment";
        public static final String TITLE = "title";
        public static final String PLACE = "place";
        public static final String DEFAULT_SORT_ORDER = " moment ASC";

        //для контент обсервера
        public static final String[] PROJECTION_ALL = {
                _ID, MOMENT, TITLE, PLACE};

        public static final HashMap<String, String> PROJECTION_MAP;
        static {
            PROJECTION_MAP = new HashMap<>();
            PROJECTION_MAP.put(_ID, _ID);
            PROJECTION_MAP.put(MOMENT, MOMENT);
            PROJECTION_MAP.put(TITLE, TITLE);
            PROJECTION_MAP.put(PLACE, PLACE);
        }
    }



    //потом можно использоать для объединений
    public static interface CommonColumns extends BaseColumns {

    }
}
