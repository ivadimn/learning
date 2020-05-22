package org.ivadimn.experiment;

import java.io.Serializable;

/**
 * Created by vadim on 09.09.16.
 */
public class Person  implements Serializable {

    private String fname;
    private String sname;
    private int age;
    private double salary;

    public Person(String fname, String sname,  int age, double salary) {
        this.age = age;
        this.fname = fname;
        this.salary = salary;
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return fname + " " + sname + " " + age + " " + salary;
    }
}
