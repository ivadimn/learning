package ru.ivadimn.android0108.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ivadimn.android0108.R;

/**
 * Created by vadim on 18.06.2017.
 */

public class OtherFragment extends NavigationFragment {

    public static final String TAG = "OtherFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.other_layout, container, false);
    }

}
