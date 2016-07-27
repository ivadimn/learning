package org.ivadimn.animtest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        switch(id) {
            case R.id.mi_scale_id:
                scaleAnimation();
                return true;
            case R.id.mi_rotate_id:
                rotateAnimation();
                return true;
            case R.id.mi_alpha_id:
                complexAnimation();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void scaleAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        textView.startAnimation(anim);

    }

    private void rotateAnimation() {
        float dest = 360f;
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(textView,
                "rotation", dest);
        animation1.setDuration(2000);
        animation1.start();
    }

    private void complexAnimation() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(textView, "alpha",
                0f);
        fadeOut.setDuration(2000);
        ObjectAnimator mover = ObjectAnimator.ofFloat(textView,
                "translationX", -500f, 0f);
        mover.setDuration(2000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(textView, "alpha",
                0f, 1f);
        fadeIn.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(mover).with(fadeIn).after(fadeOut);
        animatorSet.start();
    }

    private void otherAnimation() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.anim.property_animator);
        set.setTarget(textView);
        set.start();
    }
}