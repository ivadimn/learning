package ru.ivadimn.notescompanion.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by vadim on 11.12.16.
 */

public class PagerFragment extends Fragment {

    protected String title;
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
