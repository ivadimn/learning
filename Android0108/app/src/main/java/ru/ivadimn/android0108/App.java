package ru.ivadimn.android0108;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ru.ivadimn.android0108.model.ObjectInfo;
import ru.ivadimn.android0108.model.Repository;

/**
 * Created by vadim on 18.06.2017.
 */

public class App extends Application {


    private int[] drawers_imgs = {R.drawable.kiprenskii, R.drawable.shedrin,
    R.drawable.tropinin, R.drawable.venetsianov, R.drawable.bryullov,
    R.drawable.a_ivanov, R.drawable.fedotov};

    public static final String PACKAGE = "ru.ivadimn.android0108";
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
    public static App getInctance() {
        return instance;
    }

    public List<ObjectInfo> getObjects(String type) {
        switch(type) {
            case Repository.DRAWERS:
                return getDrawers();

            default:
                return null;
        }
    }


    private List<ObjectInfo> getDrawers() {
        List<ObjectInfo> list = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.drawers_name);
        String[] descs = getResources().getStringArray(R.array.drawers_description);
        for (int i = 0; i < titles.length; i++) {
            list.add(new ObjectInfo(titles[i], descs[i], drawers_imgs[i]));
        }
        return list;
    }


}
