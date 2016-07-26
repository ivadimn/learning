package org.ivadimn.animtest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
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
                TimeInterpolator ti = new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float v) {
                        return 0;
                    }
                }
                ObjectAnimator anim = ObjectAnimator.ofFloat(image, "rotation", 0f, 90f);
                anim.setDuration(2000);                  // Duration in milliseconds
                anim.setInterpolator(TimeInterpolator);  // E.g. Linear, Accelerate, Decelerate
                anim.start()
                AnimatorSet animSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                        R.anim.property_animator);
                animSet.setTarget(image);
                animSet.start();
            }
        });
    }
}
