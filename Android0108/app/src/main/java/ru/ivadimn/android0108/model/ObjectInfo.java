package ru.ivadimn.android0108.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vadim on 18.06.2017.
 */

public class ObjectInfo implements Parcelable{

    public static final String TAG_IMAGE = "IMAGE";
    public static final String TAG_NAME = "NAME";
    public static final String TAG_DESCRIPTION = "DESCRIPTION";


    private String title;
    private String description;
    private int image;

    public ObjectInfo() {
        //no-op
    }

    public ObjectInfo(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    protected ObjectInfo(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readInt();
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(image);
    }

    public static final Creator<ObjectInfo> CREATOR = new Creator<ObjectInfo>() {
        @Override
        public ObjectInfo createFromParcel(Parcel in) {
            return new ObjectInfo(in);
        }

        @Override
        public ObjectInfo[] newArray(int size) {
            return new ObjectInfo[size];
        }
    };
}
