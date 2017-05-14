package ru.ivadimn.contactsbackup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vadim on 14.05.2017.
 */

public class RVItemListener implements RecyclerView.OnItemTouchListener {

    public interface OnRVItemClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    private OnRVItemClickListener mListener;

    private GestureDetector detector;

    public RVItemListener(Context context, final RecyclerView rv, OnRVItemClickListener listener) {
        this.mListener = listener;
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(), e.getY());
                if (view != null && mListener != null) {
                    mListener.onLongClick(view, rv.getChildAdapterPosition(view));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (view != null && mListener != null && detector.onTouchEvent(e)) {
            mListener.onClick(view, rv.getChildAdapterPosition(view));
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
