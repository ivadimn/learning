package ru.ivadimn.notescompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by vadim on 12.10.16.
 */

public class Note  implements Serializable {

    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String INDEX = "INDEX";

    private long _id;
    private String title;
    private String content;
    private long moment;
    boolean readyToDelete;

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

    public Note(long id, String title, String content, long moment) {
        this._id = id;
        this.title = title;
        this.content = content;
        this.moment = moment;
    }

    protected Note(Parcel in) {

        title = in.readString();
        content = in.readString();
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    public boolean isReadyToDelete() {
        return readyToDelete;
    }
    public void setReadyToDelete(boolean is ) {
        this.readyToDelete = is;
    }


}
