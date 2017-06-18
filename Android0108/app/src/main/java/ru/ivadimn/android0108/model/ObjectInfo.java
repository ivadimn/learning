package ru.ivadimn.android0108.model;

/**
 * Created by vadim on 18.06.2017.
 */

public class ObjectInfo {
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
}
