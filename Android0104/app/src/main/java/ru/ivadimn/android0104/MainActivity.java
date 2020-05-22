package ru.ivadimn.android0104;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";
    private ConstraintLayout constraintLayout;
    private TextView tvMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.main_activity_id);
        tvMode = (TextView) findViewById(R.id.tv_multi_window_id);

        if (isInMultiWindowMode() || isInPictureInPictureMode())
            setActivityRes(Color.YELLOW, getString(R.string.multi_window));
    }


    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
         super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode)
            setActivityRes(Color.YELLOW, getString(R.string.multi_window));
        else
            setActivityRes(Color.WHITE, "");
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (isInPictureInPictureMode)
            setActivityRes(Color.YELLOW, getString(R.string.multi_window));
        else
            setActivityRes(Color.WHITE,"");
    }


    private void setActivityRes(int color, String msg) {
        constraintLayout.setBackgroundColor(color);
        tvMode.setText(msg);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_calc_id :
                Intent calcIntent = new Intent(this, CalcActivity.class);
                startActivity(calcIntent);
                break;
            case R.id.btn2_id:
                Intent progressIntent = new Intent(this, ProgressActivity.class);
                startActivity(progressIntent);
                break;

        }
    }

 }
