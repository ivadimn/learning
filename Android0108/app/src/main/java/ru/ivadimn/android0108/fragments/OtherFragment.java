package ru.ivadimn.android0108.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ivadimn.android0108.R;

/**
 * Created by vadim on 18.06.2017.
 */

public class OtherFragment extends NavigationFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.other_layout, container, false);
    }
}
