package ru.ivadimn.android0103.model;

/**
 * Created by vadim on 01.06.17.
 */

public class Person  {
    private String name;
    private String phone;
    private String email;

    public Person() {
        //no-op
    }

    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Имя:     " + name + "\n");
        sb.append("Телефон: " + phone + "\n");
        sb.append("E-mail : " + email + "\n");
        return sb.toString();
    }
}
