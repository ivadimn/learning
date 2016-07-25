package org.ivadimn.animtest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView image = (TextView) findViewById(R.id.text_id);
        final Button btnStart = (Button) findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.combination);
                image.startAnimation(anim);*/
                AnimatorSet animSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                        R.anim.property_animator);
                animSet.setTarget(image);
                animSet.start();
            }
        });
    }
}
