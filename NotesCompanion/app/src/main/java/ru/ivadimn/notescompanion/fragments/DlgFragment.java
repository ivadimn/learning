package ru.ivadimn.notescompanion.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.model.Note;

/**
 * Created by vadim on 12.03.17.
 */

public class DlgFragment extends AppCompatDialogFragment {
    public static final String TAG = "EDIT_DIALOG";


    private TextView tvText;
    private TextView tvTitle;
    private View view;
    private LayoutInflater inflater;


    public interface DlgInterface  {
        public void onOkClick(int pos, String title, String content);
        public void onCancelClick();
    }

    private DlgInterface listener;
    public void setListener(DlgInterface listener) {
        this.listener = listener;
    }

    public DlgFragment() {

    }

    public static DlgFragment getDlgFragment(DlgInterface linstener) {
        DlgFragment dlg = new DlgFragment();
        dlg.setListener(linstener);
        return dlg;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.note));
        builder.setIcon(android.R.drawable.edit_text);
        Bundle bundle = getArguments();
        final int pos = bundle.getInt(Note.INDEX);
        initUI(bundle);

        builder.setView(view);
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onOkClick(pos, getTitle(), getText());
            }
        });
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void initUI(Bundle bundle) {
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.note_dlg, null);
        tvTitle = (TextView) view.findViewById(R.id.edit_title_id);
        tvText = (TextView) view.findViewById(R.id.edit_text_id);
        tvTitle.setText(bundle.getString(Note.TITLE, ""));
        tvText.setText(bundle.getString(Note.TEXT, ""));
    }

    private String getTitle() {
        return tvTitle.getText().toString();
    }

    private String getText() {
        return tvText.getText().toString();
    }
}
