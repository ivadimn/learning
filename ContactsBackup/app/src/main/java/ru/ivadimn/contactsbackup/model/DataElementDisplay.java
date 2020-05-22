package ru.ivadimn.contactsbackup.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.StringCharacterIterator;

/**
 * Created by vadim on 20.05.2017.
 */

public class DataElementDisplay implements Parcelable {

    public static final String LIST_ELEMENT = "DISPLAY_ELEMENTS";

    private String mimeType;
    private String[] data;

    public DataElementDisplay(String mimeType, String ... vals) {
        this.mimeType = mimeType;
        data = vals.clone();

    }

    protected DataElementDisplay(Parcel in) {
        mimeType = in.readString();
        data = new String[in.readInt()];
        in.readStringArray(data);
    }

    public String[] getData() {
        return data;
    }
    public String getMimeType() {
        return mimeType;
    }

    public static final Creator<DataElementDisplay> CREATOR = new Creator<DataElementDisplay>() {
        @Override
        public DataElementDisplay createFromParcel(Parcel in) {
            return new DataElementDisplay(in);
        }

        @Override
        public DataElementDisplay[] newArray(int size) {
            return new DataElementDisplay[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mimeType);
        dest.writeInt(data.length);
        dest.writeStringArray(data);
    }
}
