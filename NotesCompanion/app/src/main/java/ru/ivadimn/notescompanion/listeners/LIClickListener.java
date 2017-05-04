package ru.ivadimn.notescompanion.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vadim on 04.05.17.
 */

public class LIClickListener implements RecyclerView.OnItemTouchListener {

    public static final String TAG = "LIClickListener";

    public static interface ItemClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    private ItemClickListener mListener;
    private GestureDetector detector;
    public LIClickListener(Context context, final RecyclerView rv, ItemClickListener listener) {
        this.mListener = listener;
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "Gesture.onSingleTapUp");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && mListener != null)
                    mListener.onLongClick(child, rv.getChildAdapterPosition(child));
                Log.d(TAG, "Gesture.onLongPress");
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (mListener != null && view != null && detector.onTouchEvent(e) ) {
            mListener.onClick(view, rv.getChildAdapterPosition(view));
            Log.d(TAG, "onInterceptTouchEvent - true");
            return true;
        }
        Log.d(TAG, "onInterceptTouchEvent - false");
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


}
