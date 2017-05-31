package ru.ivadimn.android0103.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.ivadimn.android0103.R;
import ru.ivadimn.android0103.model.Predicate;

public class TestActivity extends AppCompatActivity {

    public static final String TOTAL_COUNT = "TOTAL_COUNT";
    public static final String RIGHT_COUNT = "RIGHT_COUNT";

    private List<Predicate> predicates = new ArrayList<>();
    private TextView tvContent;
    private int currentIndex = 0;
    private int rightCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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

        if (val == tag) {
            Toast.makeText(tvContent.getContext(), R.string.true_answer, Toast.LENGTH_SHORT).show();
            rightCount++;
        }
        else
            Toast.makeText(tvContent.getContext(), R.string.false_answer, Toast.LENGTH_SHORT).show();

        nextPredicate();
    }

    private void nextPredicate() {
        if (++currentIndex < predicates.size()) {
            String content = predicates.get(currentIndex).getContent();
            //попытка сделать смену текста с задержкой
            new Timer().schedule(new ShowMessageOnTimer(content), 2000);
        }
        else {
            Toast.makeText(this, R.string.finish, Toast.LENGTH_SHORT).show();
            //что бы не сразу Activity закрылась
            new Timer().schedule(new FinishOnTimer(), 4000);
        }
    }


    class ShowMessageOnTimer extends TimerTask {

        private String message;
        public ShowMessageOnTimer(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvContent.setText(message);
                }
            });
        }
    }

    class FinishOnTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    endTest();
                }
            });
        }
    }
    private void endTest() {
        Intent intent = new Intent();
        intent.putExtra(TOTAL_COUNT, predicates.size());
        intent.putExtra(RIGHT_COUNT, rightCount);
        setResult(RESULT_OK, intent);
        finish();
    }
}
