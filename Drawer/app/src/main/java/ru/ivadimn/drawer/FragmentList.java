package ru.ivadimn.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by vadim on 10.01.17.
 */

public class FragmentList extends Fragment {

    public static final String TAG = "FRAGMENT_LIST";

    private ListView listView;
    private String[] cats;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        cats = getResources().getStringArray(R.array.cats_array_ru);
        listView = (ListView) view.findViewById(R.id.list_view_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cats);
        listView.setAdapter(adapter);
        Log.d(TAG, "OnCreateview - finishing ..... ");
        return view;
    }
}
