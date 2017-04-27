package ru.ivadimn.notescompanion.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by vadim on 27.04.17.
 */

public class RVItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnRVItemClickListener mListener;

    public interface OnRVItemClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }


    GestureDetector gestureDetector;

    public RVItemClickListener(Context context, final RecyclerView rv,  OnRVItemClickListener listener) {
        this.mListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && mListener != null)
                    mListener.onLongClick(child, rv.getChildAdapterPosition(child));
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child != null && mListener != null && gestureDetector.onTouchEvent(e)) {
            mListener.onClick(child, rv.getChildAdapterPosition(child));
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
