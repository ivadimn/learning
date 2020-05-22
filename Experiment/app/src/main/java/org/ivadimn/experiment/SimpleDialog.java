package org.ivadimn.experiment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by vadim on 12.08.16.
 */
public class SimpleDialog extends AppCompatDialog {
    public SimpleDialog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        setTitle("");


    }



}
