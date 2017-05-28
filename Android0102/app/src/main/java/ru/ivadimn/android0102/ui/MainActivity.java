package ru.ivadimn.android0102.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0102.R;
import ru.ivadimn.android0102.model.Predicate;

public class MainActivity extends AppCompatActivity {

    private List<Predicate> predicates = new ArrayList<>();
    private TextView tvContent;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initUI();
        tvContent.setText(predicates.get(currentIndex).getContent());
    }

    private void initData() {
        String[] ps = getResources().getStringArray(R.array.perdicates);
        String[] vals = getResources().getStringArray(R.array.values);
        for (int i = 0; i < ps.length; i++) {
            predicates.add(new Predicate(ps[i], Boolean.valueOf(vals[i])));
        }
    }

    private void initUI() {
        tvContent = (TextView) findViewById(R.id.tv_content_id);
    }

    public void onClick(View view) {
        boolean val = predicates.get(currentIndex).isRight();
        boolean tag = Boolean.valueOf((String) view.getTag());

        if (val == tag)
            Toast.makeText(tvContent.getContext(), R.string.true_answer, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(tvContent.getContext(), R.string.false_answer, Toast.LENGTH_SHORT).show();

        nextPredicate();
    }

    private void nextPredicate() {
        if (++currentIndex < predicates.size()) {
            String content = predicates.get(currentIndex).getContent();
            tvContent.setText(content);
        }
        else {
            Toast.makeText(this, R.string.finish, Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }


}
