package ru.ivadimn.contactsbackup;

import android.app.Application;
import android.provider.ContactsContract;

/**
 * Created by vadim on 20.05.2017.
 */

public class App extends Application {

   private static App instance;

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    public String getStringFromResource(int resId) {
        String s = this.getString(resId);
        return s;
    }
}
