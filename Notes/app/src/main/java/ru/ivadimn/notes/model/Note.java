package ru.ivadimn.notes.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vadim on 12.10.16.
 */

public class Note implements Serializable {

    private String title;
    private String content;
    private Date moment;

    public Note(String title, String content, Date moment) {
        this.title = title;
        this.content = content;
        this.moment = moment;
    }


}
