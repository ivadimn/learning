package ru.ivadimn.android0107.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0107.App;
import ru.ivadimn.android0107.R;
import ru.ivadimn.android0107.adapters.PersonAdapter;
import ru.ivadimn.android0107.model.Person;
import ru.ivadimn.android0107.model.Repository;

/**
 * Created by vadim on 14.06.2017.
 */

public class PersonListFragment extends Fragment {

    public static final String TAG = "LIST_PERSONS";

    private RecyclerView list;
    private List<Person> persons;
    private PersonAdapter adapter;
    private FloatingActionButton fab;
    private Menu menu;


    public interface OnSelectItemListener {
        public void onSelectItem(int position);
        public void setDeleteMode(boolean mode);
    }

    private OnSelectItemListener selectListener;
    public PersonListFragment() {

    }

    public static PersonListFragment getFragment() {
        return new PersonListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectItemListener)
             selectListener = (OnSelectItemListener) context;
        else
            throw new RuntimeException("context must implement OnSelectItemListener interface");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        persons = Repository.getPersons();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_list_fragment, container, false);

        list = (RecyclerView) view.findViewById(R.id.person_list_id);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PersonAdapter(listener);
        adapter.updateData(persons);
        list.setAdapter(adapter);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onSelectItem(-1);
            }
        });
        return view;
    }



    //обработка событий адаптера
    private PersonAdapter.PersonClickListener listener = new PersonAdapter.PersonClickListener() {
        @Override
        public void onClick(View view, int position) {
            selectListener.onSelectItem(position);
        }

        @Override
        public void onLongClick(View view, int position) {
            //menu.setGroupVisible(R.id.group_id, true);
            adapter.setDeleteMode(true);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            selectListener.setDeleteMode(true);
        }
    };


    public void deletePersons() {
        List<Person> temp = new ArrayList<>(persons);
        for (Person p : temp) {
            if (p.isDelete()) {
                persons.remove(p);
            }
        }
        cancelDelete();
    }

    public void updateList(boolean dataonly) {
        adapter.notifyDataSetChanged();
    }

    public void cancelDelete() {
        adapter.setDeleteMode(false);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }

}
