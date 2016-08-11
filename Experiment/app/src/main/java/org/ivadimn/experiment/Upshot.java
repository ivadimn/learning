package org.ivadimn.experiment;

/**
 * Created by vadim on 26.06.16.
 */
public class Upshot {
    private String title;
    private boolean value;

    public Upshot() {
        //no-op
    }
    public Upshot(String title, boolean value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
