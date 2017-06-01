package ru.ivadimn.android0103.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import ru.ivadimn.android0103.R;

/**
 * Created by vadim on 01.06.17.
 */

public class InfoDialog extends AppCompatDialogFragment {

    public static final String TAG = "INFO_DIALOG";
    private static final String TITLE = "TITLE";
    private static final String INFO = "INFO";

    public interface DialogListener {
        public void onPositiveClick(DialogInterface dialog);
    }


    private DialogListener mListener;

    public InfoDialog() {
    }

    public static InfoDialog getDialog(String title, String info, DialogListener listener) {
        InfoDialog dlg = new InfoDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(INFO, info);
        dlg.setListener(listener);
        dlg.setArguments(bundle);
        return dlg;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        builder.setTitle(bundle.getString(TITLE));
        builder.setMessage(bundle.getString(INFO));
        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null)
                    mListener.onPositiveClick(dialog);
                else
                    dismiss();
            }
        });
        return builder.create();
    }

    public void setListener(DialogListener listener) {
        this.mListener = listener;
    }
}
