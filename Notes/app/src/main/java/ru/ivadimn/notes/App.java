package ru.ivadimn.notes;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.notes.database.DataManage;
import ru.ivadimn.notes.database.Values;
import ru.ivadimn.notes.model.Note;
import ru.ivadimn.notes.model.Note1;
import ru.ivadimn.notes.ui.adapters.NotesAdapter;

/**
 * Created by vadim on 28.02.17.
 */

public class App extends Application {
    public static final String NOTES_FILE = "notes.txt";

    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * получить список заметок
     * @return
     */
    public List<Note> getNotes() {
        File file = new File(getFilesDir(), NOTES_FILE);
        if (!file.exists()) return null;

        List<Note> noteList = new ArrayList<>();
        try {
            ObjectInputStream inObj = new ObjectInputStream(new FileInputStream(file));
            Log.d("GET_NOTES", "Создан объект чтения заметок");
            Note[] notes = (Note[]) inObj.readObject();
            Log.d("GET_NOTES", "Объекты прочитаны");
            for (Note n : notes) {
                noteList.add(n);
            }
            Log.d("GET_NOTES", "Создан список");
        } catch (IOException e) {
            Log.d("GET_NOTES", e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    public void saveNotes(List<Note> noteList) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(getFilesDir(), NOTES_FILE), false);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            Note[] notes = new Note[noteList.size()];

            for (int i = 0; i < noteList.size(); i++) {
                notes[i] = noteList.get(i);
            }

            objOut.writeObject(notes);
            objOut.close();
            Log.d("saveNotes", "FINISH");
        }
        catch (FileNotFoundException ex) {
            Log.d("APP", ex.getMessage());
        }
        catch(IOException ex) {
            Log.d("APP", ex.getMessage());
        }
    }

    /**
     * получить список заметок из базы данных (DBFLOW)
     * @return
     */
    public List<Note1> getNotes1() {
        return new Select().from(Note1.class).queryList();

    }
}
