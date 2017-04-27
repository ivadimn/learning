package ru.ivadimn.notescompanion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vadim on 27.04.17.
 */

public  class PersonGenerator  {

    private static int COUNT = 10;
    private static String[] fNames = {"Pete", "John", "Kurt", "Anna", "David",
            "Sara", "Julia", "Vins", "Brad", "Brus"};
    private static String[] sNames = {"Smith", "Sazerlend", "Roberts", "Rassle", "Willis",
            "McDonald", "Graives", "Mia", "Norris", "Duglas"};
    private final static Random RANDOM = new Random();

    public static List<Person> generate(int count) {
        List<Person> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String name = fNames[RANDOM.nextInt(COUNT)] + " " + sNames[RANDOM.nextInt(COUNT)];
            String phone = String.format("%s-%s-%s-%s-%s", RANDOM.nextInt(10), RANDOM.nextInt(1000),
                    RANDOM.nextInt(1000), RANDOM.nextInt(100), RANDOM.nextInt(100));
            list.add(new Person(name, phone, 0));
        }
        return list;
    }
}
