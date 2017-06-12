package ru.ivadimn.android0106.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by vadim on 12.06.2017.
 */

public class PersonSerial implements Serializable {

    private String name;
    private String phone;
    private String email;
    private String hobbys;
    private byte[] photo;

    public PersonSerial() {
        //no-op
    }

    public PersonSerial(Person person) {
        name = person.getName();
        phone = person.getPhone();
        email = person.getEmail();
        hobbys = person.getHobbys();
        Bitmap bmp = person.getPhoto();
        if (bmp != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, out);
            photo = out.toByteArray();
        }
    }

    public Person getPerson() {
        Person p = new Person(name, phone, email, hobbys);
        if (photo != null) {
            p.setPhoto(BitmapFactory.decodeByteArray(photo, 0, photo.length));
        }
        return p;
    }
}
