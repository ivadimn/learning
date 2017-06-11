package ru.ivadimn.android0106.model;

/**
 * Created by vadim on 11.06.2017.
 */

public class Person {

    private int _id;
    private String name;
    private String phone;
    private String email;
    private String organization;
    private String hobbys;

    public Person() {
        //no-op
    }

    public Person(String name, String phone, String email, String organization, String hobbys) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.organization = organization;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }
}
