package ru.ivadimn.notescompanion.fragments;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.adapters.PictureAdapter;
import ru.ivadimn.notescompanion.listeners.RVItemClickListener;
import ru.ivadimn.notescompanion.model.Person;
import ru.ivadimn.notescompanion.model.PersonGenerator;

/**
 * Created by vadim on 12.03.17.
 */

public class PictureFragment extends PagerFragment {

    private RecyclerView rv;
    private List<Person> list;
    private PictureAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle arg) {
        super.onCreate(arg);
        list = PersonGenerator.generate(20);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.person_rvlist_id);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PictureAdapter(list);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new RVItemClickListener(getContext(), rv,
                new RVItemClickListener.OnRVItemClickListener() {

                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(getActivity(), "Short Click", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "Long Click", Toast.LENGTH_SHORT).show();
                    }
                }
        ));
        return view;
    }
}
