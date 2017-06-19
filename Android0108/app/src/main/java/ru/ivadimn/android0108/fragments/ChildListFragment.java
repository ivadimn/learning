package ru.ivadimn.android0108.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.ivadimn.android0108.App;
import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.adapters.ObjectAdapter;
import ru.ivadimn.android0108.model.ObjectInfo;
import ru.ivadimn.android0108.model.Repository;

/**
 * Created by vadim on 18.06.2017.
 */

public class ChildListFragment extends Fragment {

    private static final String TYPE = "TYPE";
    private static final String TAG = "ChildListFragment";

    public interface OnChildClickListener {
        public void onChildClick(View v, ObjectInfo object);
    }

    private OnChildClickListener childListener;

    private List<ObjectInfo> objects;
    private RecyclerView listView;
    private ObjectAdapter adapter;


    public static ChildListFragment getInstance(String type) {
        ChildListFragment fragment = new ChildListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnChildClickListener)
            childListener = (OnChildClickListener) context;
        else if (getParentFragment() instanceof OnChildClickListener) {
            childListener = (OnChildClickListener) getParentFragment();
        }
        else {
            throw new RuntimeException("This context is not OnChildClickListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        Repository.resetObjects();
        if (bundle != null)
            objects = Repository.getObjects(bundle.getString(TYPE));
        else
            objects = Repository.getObjects(Repository.DRAWERS);
        Log.d(TAG, "OnCreate");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_layout, container, false);
        listView = (RecyclerView) view.findViewById(R.id.list_objects_id);
        listView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        listView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 24;
            }
        });
        adapter = new ObjectAdapter(listener);
        listView.setAdapter(adapter);
        adapter.updateData(objects);
        return view;
    }

    private ObjectAdapter.OnObjectClickListener listener = new ObjectAdapter.OnObjectClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (childListener != null)
                childListener.onChildClick(view, objects.get(position));
        }
    };


}
