package ru.ivadimn.android0108.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.activities.DescriptionActivity;
import ru.ivadimn.android0108.model.ObjectInfo;
import ru.ivadimn.android0108.model.Repository;

/**
 * Created by vadim on 18.06.2017.
 */

public class ChildDetailFragment extends Fragment {

    private static final String CONTENT = "CONTENT";

    private ObjectInfo object;
    private TextView textView;
    private Button btnView;

    private static ChildDetailFragment instance;

    public static ChildDetailFragment getInstance() {
        instance = new ChildDetailFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            object = (ObjectInfo) savedInstanceState.getParcelable(CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description, container, false);
        textView = (TextView) view.findViewById(R.id.description_id);
        if (object != null)
            textView.setText(object.getDescription());
        btnView = (Button) view.findViewById(R.id.btn_view_id);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showObject();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CONTENT, object);
    }

    public void setObject(ObjectInfo object) {
        this.object = object;
        textView.setText(object.getDescription());
    }

    private void showObject() {
        Intent intent = new Intent(getContext(), DescriptionActivity.class);
        intent.putExtra(ObjectInfo.TAG_NAME, object.getTitle());
        intent.putExtra(ObjectInfo.TAG_DESCRIPTION, object.getDescription());
        intent.putExtra(ObjectInfo.TAG_IMAGE, object.getImage());
        startActivity(intent);
    }

}
