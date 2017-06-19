package ru.ivadimn.android0108.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.model.Repository;

/**
 * Created by vadim on 18.06.2017.
 */

public class ChildDetailFragment extends Fragment {

    private static final String CONTENT = "CONTENT";

    private String description;
    private TextView textView;

    private static ChildDetailFragment instance;

    public static ChildDetailFragment getInstance() {
        instance = new ChildDetailFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description, container, false);
        textView = (TextView) view.findViewById(R.id.description_id);
        return view;
    }

    public void setDescription(String description) {
        this.description = description;
        textView.setText(description);
    }
}
