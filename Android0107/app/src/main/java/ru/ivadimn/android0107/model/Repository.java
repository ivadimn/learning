package ru.ivadimn.android0107.model;

import java.util.List;

import ru.ivadimn.android0107.App;

/**
 * Created by vadim on 15.06.17.
 */

public class Repository  {
    private static List<Person> persons;

    public static List<Person> getPersons() {
        if (persons == null) {
            persons = App.getInstance().loadPersons();
        }
        return persons;
    }

    public static Person getPerson(int index) {
        if (persons == null) return null;
        else
            return (index > -1 && index < persons.size()) ? persons.get(index) : null;
    }

    public static void setPerson(int index, Person p) {
        persons.set(index, p);
    }

    public static void addPerson(Person p) {
        persons.add(p);
    }

    public static void savePersons() {
        App.getInstance().savePersons(persons);
    }

}
