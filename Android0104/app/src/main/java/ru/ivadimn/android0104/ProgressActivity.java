package ru.ivadimn.android0104;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {

    private static final String PROGRESS_VALUE = "PROGRESS_VALUE";

    private final int MAX_COUNT = 20;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private Handler handler;
    private int progressValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = (ProgressBar)findViewById(R.id.progress_id);
        tvProgress = (TextView) findViewById(R.id.tv_progress_id);
        handler = new Handler(Looper.myLooper());
        if (savedInstanceState != null) {
            progressValue = savedInstanceState.getInt(PROGRESS_VALUE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setProgressValue(progressValue);
        handler.post(load);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(load);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PROGRESS_VALUE, progressValue);
    }

    private void setProgressValue(int value) {
        progressBar.setProgress(value);
        tvProgress.setText("Загружено файлов: " + value);

    }

    private Runnable load = new Runnable() {
        @Override
        public void run() {
            if (progressValue <  MAX_COUNT) {
                setProgressValue(++progressValue);
                handler.postDelayed(load, 500);
            }
            else {
                try {
                    Thread.sleep(3000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
