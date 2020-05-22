package ru.ivadimn.notescompanion.interfaces;

import android.view.View;

/**
 * Created by vadim on 11.12.16.
 */

public interface Listener {
    public  void onImageClick(View v, int i);
    public void onTextClick(View v, int i);
    public void onCardClick(View v, int i);
    public void onCheckBoxClick(View v, int i);
}
