package ru.ivadimn.notes.model;

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

public class Note implements Serializable, Parcelable {

    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String INDEX = "INDEX";

    private String title;
    private String content;
    private long moment;

    public static final Calendar CALENDAR = new GregorianCalendar();
    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

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
}
