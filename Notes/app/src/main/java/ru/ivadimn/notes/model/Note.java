package ru.ivadimn.notes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vadim on 12.10.16.
 */

public class Note implements Serializable, Parcelable {

    private String title;
    private String content;
    private Date moment;

    public Note(String title, String content, Date moment) {
        this.title = title;
        this.content = content;
        this.moment = moment;
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
}
