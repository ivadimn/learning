package ru.ivadimn.android0108.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ru.ivadimn.android0108.R;

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
    public void onStart() {
        super.onStart();
        getActivity().setTitle(title);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(R.string.app_name);
    }
}
