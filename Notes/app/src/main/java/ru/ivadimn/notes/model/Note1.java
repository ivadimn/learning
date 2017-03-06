package ru.ivadimn.notes.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ru.ivadimn.notes.dbflow.NoteDatabase;

/**
 * Created by vadim on 05.03.2017.
 */
@Table(database = NoteDatabase.class)
public class Note1 extends BaseModel {

    public Note1() {
        //no-op
    }
    public Note1(String title, String content)  {
        this.title = title;
        this.content = content;
        this.moment = CALENDAR.getTime().getTime();
    }

    @Column
    @PrimaryKey(autoincrement = true)
    private int _id;
    @Column
    private String title;
    @Column
    private String content;
    private long moment;
    private boolean checked = false;

    public static final Calendar CALENDAR = new GregorianCalendar();
    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public void setMoment(Date dateTime) {
        this.moment = dateTime.getTime();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getMomentString() {
        return format.format(moment);
    }
}
