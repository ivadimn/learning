package ru.ivadimn.android0107.model;

import android.graphics.Bitmap;

/**
 * Created by vadim on 14.06.2017.
 */

public class Person {
    private int _id;
    private String name;
    private String phone;
    private String email;
    private String hobbys;
    private Bitmap photo;

    private boolean isDelete = false;

    public Person() {
        //no-op
    }

    public Person(String name, String phone, String email, String hobbys) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hobbys = hobbys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
