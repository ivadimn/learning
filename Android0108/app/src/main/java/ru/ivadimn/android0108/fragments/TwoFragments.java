package ru.ivadimn.android0108.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.model.Repository;

/**
 * Created by vadim on 18.06.2017.
 */

public class TwoFragments extends NavigationFragment implements ChildListFragment.OnChildClickListener{

    private ChildListFragment listFragment;
    private ChildDetailFragment detailFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listFragment = ChildListFragment.getInstance(Repository.DRAWERS);
        listFragment.setChildListener(this);
        detailFragment = ChildDetailFragment.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment_layout, container, false);

        getChildFragmentManager().beginTransaction()
                .add(R.id.list_container_id, listFragment)
                .add(R.id.detail_container_id, detailFragment)
                .commit();

        return view;
    }

    @Override
    public void onChildClick(View v, String description) {
        detailFragment.setDescription(description);
    }
}
