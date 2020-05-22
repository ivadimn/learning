package org.ivadimn.experiment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by vadim on 24.06.16.
 */
public class RadioFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton [] buttons;
    String [] list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_group_fragment, null);

        radioGroup = (RadioGroup) view.findViewById(R.id.reply_rg_id);
        buttons = new RadioButton[list.length];
        for (int i = 0; i < list.length; i++) {
            buttons[i] = new RadioButton(container.getContext());
            buttons[i].setText(list[i]);
            buttons[i].setChecked(false);
            radioGroup.addView(buttons[i], i);
        }
        return view;
    }

    public void setList(String [] list) {
        this.list = list;
    }
}
