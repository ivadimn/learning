package ru.ivadimn.android0107;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0107.model.Person;
import ru.ivadimn.android0107.model.PersonSerial;

/**
 * Created by vadim on 14.06.2017.
 */

public class App extends Application {

    public static final String FILE = "persons";

    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public void savePersons(List<Person> p) {
        File file = new File(getFilesDir(), FILE);

        PersonSerial[] ps =  new PersonSerial[p.size()];
        for (int i = 0; i < p.size(); i++) {
            ps[i] = new PersonSerial(p.get(i));
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(ps);
            out.flush();
            out.close();
        } catch (IOException e) {
            Log.d("UTILS_CLASS", e.getMessage());
            e.printStackTrace();
        }
    }

    public  List<Person> loadPersons() {
        File file = new File(getFilesDir(), FILE);

        if (!file.exists()) {
            return generatePersons();
        }
        PersonSerial[] ps;
        List<Person> p = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            ps = (PersonSerial[]) in.readObject();
            for (int i = 0; i < ps.length; i++) {
                p.add(ps[i].getPerson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }


    private List<Person> generatePersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("vadim ivanov", "+79116789054", "aaaa@mail.com", "1111, 11111"));
        persons.add(new Person("peter sidoprov", "+79566789054", "bbb@mail.com", "1111, 11111"));
        persons.add(new Person("vasia petrov", "+79566789054", "bbb@mail.com", "1111, 11111"));
        persons.add(new Person("misha kuznecov", "+79566789054", "bbb@mail.com", "1111, 11111"));
        return persons;
    }
}
