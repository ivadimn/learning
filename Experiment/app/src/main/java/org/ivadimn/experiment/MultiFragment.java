package org.ivadimn.experiment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 31.05.16.
 */
public class MultiFragment extends Fragment {

    private RecyclerView recyclerView;

    List<Upshot> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multi_value_fragment, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.answer_list_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initUpshot();
        MultiAdapter ma = new MultiAdapter(list);
        recyclerView.setAdapter(ma);
        return view;
    }

    private void initUpshot() {
        for(int i = 0; i < 10; i ++) {
            list.add(new Upshot(i + " Элемент", (i%2 > 0 ? true : false)));
        }
    }
}
