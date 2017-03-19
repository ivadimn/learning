package ru.ivadimn.notes.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

import ru.ivadimn.notes.App;
import ru.ivadimn.notes.database.NotesDatabaseHelper;

/**
 * Created by vadim on 17.03.17.
 */

public class NoteProvider extends ContentProvider {

    public static final String TAG = "NOTE_PROVIDER";

    private static final int URI_NOTES = 1;
    private static final int URI_NOTES_ID = 2;

    //карта проекций для QueryBuilder
    private static final HashMap<String, String> NOTE_PROJECTION;
    static {
        NOTE_PROJECTION = new HashMap<>();
        NOTE_PROJECTION.put(NoteProviderMetaData.NoteTableMetaData._ID,
                NoteProviderMetaData.NoteTableMetaData._ID);
        NOTE_PROJECTION.put(NoteProviderMetaData.NoteTableMetaData.TITLE,
                NoteProviderMetaData.NoteTableMetaData.TITLE);
        NOTE_PROJECTION.put(NoteProviderMetaData.NoteTableMetaData.MOMENT,
                NoteProviderMetaData.NoteTableMetaData.MOMENT);
    }
    //создание UriMatcher
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(NoteProviderMetaData.AUTHORITY, NoteProviderMetaData.NOTE_PATH, URI_NOTES);
        URI_MATCHER.addURI(NoteProviderMetaData.AUTHORITY, NoteProviderMetaData.NOTE_PATH + "/#", URI_NOTES_ID);
    }

    private NotesDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate NotePrivider");
        dbHelper = new NotesDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("TAG", "query " + uri.toString());
        //SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        //обработка uri
        switch(URI_MATCHER.match(uri)) {
            case URI_NOTES:                  //общий uri  для списка
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = NoteProviderMetaData.NoteTableMetaData.DEFAULT_SORT_ORDER;
                break;
            case URI_NOTES_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                    selection = NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                else
                    selection = selection + " AND " +
                            NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(NoteProviderMetaData.NoteTableMetaData.TABLE_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                NoteProviderMetaData.NOTE_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(URI_MATCHER.match(uri)) {
            case URI_NOTES:
                return NoteProviderMetaData.NOTE_CONTENT_TYPE;
            case URI_NOTES_ID:
                return NoteProviderMetaData.NOTE_CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "Insert, " + uri.toString());
        if (URI_MATCHER.match(uri) != URI_NOTES)
            throw new IllegalArgumentException("Wrong URI: " + uri);
        db = dbHelper.getWritableDatabase();
        long rowId = db.insert(NoteProviderMetaData.NoteTableMetaData.TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(NoteProviderMetaData.NOTE_CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "Delete, " + uri.toString());
        switch(URI_MATCHER.match(uri)) {
            case URI_NOTES:
                Log.d(TAG, "URI_NOTES");
                break;
            case URI_NOTES_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                    selection = NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                else
                    selection = selection + " AND " +
                            NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(NoteProviderMetaData.NoteTableMetaData.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "Update, " + uri.toString());
        switch(URI_MATCHER.match(uri)) {
            case URI_NOTES:
                Log.d(TAG, "URI_NOTES");
                break;
            case URI_NOTES_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                    selection = NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                else
                    selection = selection + " AND " +
                            NoteProviderMetaData.NoteTableMetaData._ID + "=" + id;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(NoteProviderMetaData.NoteTableMetaData.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }
}
