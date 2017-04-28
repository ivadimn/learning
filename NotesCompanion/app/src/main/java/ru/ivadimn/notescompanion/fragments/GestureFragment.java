package ru.ivadimn.notescompanion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;

/**
 * Created by vadim on 28.04.17.
 */

public class GestureFragment extends PagerFragment implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat detector;

    @Override
    public void onCreate(@Nullable Bundle args) {
        super.onCreate(args);
        detector = new GestureDetectorCompat(getActivity(), this);
        detector.setOnDoubleTapListener(this);
    }





}
