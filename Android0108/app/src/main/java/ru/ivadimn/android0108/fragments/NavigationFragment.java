package ru.ivadimn.android0108.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by vadim on 18.06.2017.
 */
public class NavigationFragment extends Fragment {
    protected String title;
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).setTitle(title);
        }
    }

}
