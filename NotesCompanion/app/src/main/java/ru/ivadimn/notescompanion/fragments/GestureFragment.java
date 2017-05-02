package ru.ivadimn.notescompanion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.ivadimn.notescompanion.R;

/**
 * Created by vadim on 28.04.17.
 */

public class GestureFragment extends PagerFragment implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat detector;
    private TextView tvGesture;

    @Override
    public void onCreate(@Nullable Bundle args) {
        super.onCreate(args);
        detector = new GestureDetectorCompat(getActivity(), this);
        detector.setOnDoubleTapListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gesture_fragment, container, false);
        tvGesture = (TextView) view.findViewById(R.id.tv_gesture_id);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
        return view;
    }



    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

//GestureDetector.OnGestureListener методы

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        tvGesture.setText("onSingleTapUp " + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        tvGesture.setText("onScroll " + e1.toString() + " " + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        tvGesture.setText("onLongPress " + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        tvGesture.setText("onFing " + e1.toString() + " " + e2.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        tvGesture.setText("onDown " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        tvGesture.setText("onShownPress " + e.toString());
    }
}
