package org.ivadimn.experiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectActivity extends AppCompatActivity {

    private TextView txtPerson;
    public static final String FILE_NAME = "persons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        txtPerson = (TextView) findViewById(R.id.txt_person_id);
    }

    public void onCreatePerson(View v) {

        Person[] persons = new Person[3];
        persons[0] = new Person("Pete", "Smiron", 45, 256.00);
        persons[1] = new Person("John", "Smit", 40, 4756.00);
        persons[2] = new Person("Pol", "Dik", 23, 1256.00);

        File f = new File(getFilesDir(), FILE_NAME);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject(persons);
            Log.d("OBJECT_ACTIVITY", "Person saved");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onLoadPerson(View v) {

        File f = new File(getFilesDir(), FILE_NAME);

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            Person[] person = (Person[]) in.readObject();
            StringBuilder sb = new StringBuilder();

            for (Person p :person ) {
               sb.append(p.toString() + "\n");
            }

            txtPerson.setText(sb.toString());
        }
        catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
