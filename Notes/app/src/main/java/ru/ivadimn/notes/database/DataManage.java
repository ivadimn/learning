package ru.ivadimn.notes.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.notes.App;

/**
 * Created by vadim on 05.03.2017.
 */

public class DataManage  {
    SQLiteOpenHelper openHelper;

    public DataManage() {
        openHelper = new NotesDatabaseHelper(App.getInstance().getApplicationContext());
    }

    public List<Values> selectAll(DbShema shema) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Values> values = new ArrayList<>();
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query(shema.getTableName(),
                    shema.getColumns(), null, null, null, null, null);
            int colCount = cursor.getColumnCount();
            while(cursor.moveToNext()) {
                Values value = new Values(colCount);
                for (int i = 0; i < colCount; i++) {
                    value.set(i, valueFrom(shema, cursor, i));
                }
                values.add(value);
            }
        }
        catch(Exception e) {
            Toast.makeText(App.getInstance().getBaseContext(), "Database unavailable - " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.d("NOTE_DATA", "Database unavailable - " + e.getMessage());
        }
        finally {
            cursor.close();
            db.close();
        }
        return values;
    }

    public int insert(DbEntity entity) {
        SQLiteDatabase db = null;
        ContentValues contentValues = null;
        DbShema shema = entity.getShema();
        try {
            db = openHelper.getWritableDatabase();
            contentValues = getContentValues(entity);
            db.insert(shema.getTableName(), null, contentValues);
            entity.set_id(getId(db, entity));
            Toast.makeText(App.getInstance().getBaseContext(), "Inserted ID = " + entity.get_id(),
                    Toast.LENGTH_SHORT).show();
        }
        catch(SQLiteException e) {
            Toast.makeText(App.getInstance().getBaseContext(), "Database unavailable - " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
        return 0;
    }

    public void update(DbEntity entity) {
        SQLiteDatabase db = null;
        ContentValues contentValues = null;
        try {
            db = openHelper.getWritableDatabase();
            contentValues = getContentValues(entity);
            db.update(entity.getShema().getTableName(),
                    contentValues, entity.getShema().getKeyColumn() +" = ?", new String[] {Integer.toString(entity.get_id())});
        }
        catch(SQLiteException e) {
            Toast.makeText(App.getInstance().getBaseContext(), "Database unavailable - " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
    }

    public void delete(DbEntity entity) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            db.delete(entity.getShema().getTableName(),  entity.getShema().getKeyColumn() + " = ?",
                    new String[] {Integer.toString(entity.get_id())});
        }
        catch(SQLiteException e) {
            Toast.makeText(App.getInstance().getBaseContext(), "Database unavailable - " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            db.close();
        }
    }

    //получить значение поля из курсора в зависимости от типа
    private Object valueFrom(DbShema shema, Cursor cursor, int index) {
        int typeValue = shema.getTypeColumn(index);
        switch(typeValue) {
            case DbEntity.TYPE_INTEGER :
                return cursor.getInt(index);
            case DbEntity.TYPE_LONG:
                return  cursor.getLong(index);
            case DbEntity.TYPE_STRING:
                return  cursor.getString(index);
            case DbEntity.TYPE_REAL:
                return  cursor.getDouble(index);
            case DbEntity.TYPE_BLOB:
                return new Blob(cursor.getBlob(index));
            default:
                return DbEntity.TYPE_NULL;
        }
    }


    //получить _id только что вставленной записи
    private int getId(SQLiteDatabase db, DbEntity entity) {
        DbShema shema = entity.getShema();
        Cursor cursor = null;
        int id = 0;
        try {
            cursor = db.query(shema.getTableName(),
                    new String[] {"_id"},  shema.getWhere(), entity.getValuesString(),
                    null, null, null);
            if (cursor.moveToNext()) {
                id = cursor.getInt(0);
            }
        }
        catch(Exception e) {
            Toast.makeText(App.getInstance().getBaseContext(), "Database unavailable - " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.d("NOTE_DATA", "Database unavailable - " + e.getMessage());
        }
        finally {
            cursor.close();
        }
        return id;
    }

    //сформировать contentValues для insert и update
    private ContentValues getContentValues(DbEntity entity) {
        ContentValues contentValues = new ContentValues();
        DbShema shema = entity.getShema();
        int colCount = shema.getColumnsCount();
        String[] cols = shema.getColumns();
        Values values = entity.getValues();
        for (int i = 1; i < colCount; i++) {
            valueTo(contentValues, cols[i], values.get(i), shema.getTypeColumn(i));
        }
        return contentValues;
    }

    //поместить в contentValue значение в зависимости от типа
    private void valueTo(ContentValues contentValues,
                         String column, Object value, int typeValue) {
        switch(typeValue) {
            case DbEntity.TYPE_INTEGER :
                contentValues.put(column, (int) value);
                break;
            case DbEntity.TYPE_LONG:
                contentValues.put(column, (long) value);
                break;
            case DbEntity.TYPE_STRING:
                contentValues.put(column, (String) value);
                break;
            case DbEntity.TYPE_REAL:
                contentValues.put(column, (double) value);
                break;
            case DbEntity.TYPE_BLOB:
                contentValues.put(column, ((Blob) value).getBytes());
                break;
            default:
                contentValues.put(column, (byte[]) null);
                break;
        }
    }
}
