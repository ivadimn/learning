package ru.ivadimn.android0103.model;

/**
 * Created by vadim on 28.05.2017.
 */

public class Predicate {

    private String content;
    private boolean right;

    public Predicate(String content, boolean right) {
        this.content = content;
        this.right = right;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
