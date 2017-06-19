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

    private static final String TYPE = "TYPE";


    private ChildListFragment listFragment;
    private ChildDetailFragment detailFragment;
    private String typeInfo;

    public static TwoFragments getInstance(String type) {
        TwoFragments tf = new TwoFragments();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        tf.setArguments(bundle);
        return tf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            listFragment = ChildListFragment.getInstance(bundle.getString(TYPE));
        else
            listFragment = ChildListFragment.getInstance(Repository.DRAWERS);

        detailFragment = ChildDetailFragment.getInstance();


    }

    @Override
    public void onResume() {
        super.onResume();
        getChildFragmentManager().beginTransaction()
                .add(R.id.list_container_id, listFragment)
                .add(R.id.detail_container_id, detailFragment)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment_layout, container, false);
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        getChildFragmentManager().beginTransaction()
                .remove(listFragment)
                .remove(detailFragment)
                .commit();
    }


    @Override
    public void onChildClick(View v, String description) {
        detailFragment.setDescription(description);
    }
}
