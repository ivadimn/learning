package org.ivadimn.experiment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by vadim on 12.08.16.
 */
public class SimpleDialogFragment extends AppCompatDialogFragment  {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new SimpleDialog(getActivity());
    }
}
