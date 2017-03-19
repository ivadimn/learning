package ru.ivadimn.notes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ru.ivadimn.notes.database.DbEntity;
import ru.ivadimn.notes.database.DbShema;
import ru.ivadimn.notes.database.Values;

/**
 * Created by vadim on 12.10.16.
 */

public class Note extends DbEntity implements Parcelable {

    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String INDEX = "INDEX";

    private String title;
    private String content;
    private long moment;
    private boolean checked = false;

    public static final Calendar CALENDAR = new GregorianCalendar();
    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public Note() {
        //No-op
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.moment = CALENDAR.getTime().getTime();
    }

    public Note(String title, String content, Date moment) {
        this.title = title;
        this.content = content;
        this.moment = moment.getTime();
    }

    protected Note(Parcel in) {

        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String[] data = new String[] {title, content, " "};
        parcel.writeStringArray(data);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMoment() {
        return moment;
    }

    public String getMomentString() {
        return format.format(moment);
    }

    public void setMoment(Date dateTime) {
        this.moment = dateTime.getTime();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public Values getValues() {
        return new Values(new Object[] {_id, title, content, moment});
    }

    @Override
    public void setValues(Values values) {
        _id = (int) values.get(0);
        title = (String) values.get(1);
        content = (String) values.get(2);
        moment = (long) values.get(3);
    }

    @Override
    public String[] getValuesString() {
        return new String[] {title, content, String.valueOf(moment)};
    }

    @Override
    public DbShema getShema() {
        return shema;
    }

    public static DbShema shema = new DbShema() {
        @Override
        public String getTableName() {
            return "NOTE";
        }

        @Override
        public int getTypeColumn(int index) {
            switch(index) {
                case 0:
                    return DbEntity.TYPE_INTEGER;
                case 1:
                    return DbEntity.TYPE_STRING;
                case 2:
                    return DbEntity.TYPE_STRING;
                case 3:
                    return DbEntity.TYPE_LONG;
                default:
                    return DbEntity.TYPE_NULL;
            }
        }

        @Override
        public String[] getColumns() {
            return new String[] {"_id", "title", "ntext", "moment"};
        }

        @Override
        public int getColumnsCount() {
            return 4;
        }

        @Override
        public String getKeyColumn() {
            return "_id";
        }

        /**
         * это надо переделать
         * @return
         */
        @Override
        public String getWhere() {
            return "title=? AND ntexr=? AND moment=? ";
        }
    };
}
