package org.ivadimn.experiment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    SingleFragment sgFragment;
    MultiFragment mtFragment;
    WordFragment wdFragment;
    RadioFragment rfFragment;
    boolean wasFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sgFragment = new SingleFragment();
        mtFragment = new MultiFragment();
        wdFragment = new WordFragment();
        rfFragment = new RadioFragment();
        rfFragment.setList(new String[] {"один", "два", "три", "четыре"});
        wasFragment = false;
    }


    public void onClick(View v) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        switch(v.getId()) {
            case R.id.btn_single_value:
                if (!wasFragment) {
                    fragmentTransaction.add(R.id.fragment_container, sgFragment);
                    wasFragment = true;
                }
                else
                    fragmentTransaction.replace(R.id.fragment_container, sgFragment);
                break;
            case R.id.btn_multi_value:
                Intent intent = new Intent(this, MultiActivity.class);
                startActivity(intent);
                /*if (!wasFragment) {
                    fragmentTransaction.add(R.id.fragment_container, mtFragment);
                    wasFragment = true;
                }
                else
                    fragmentTransaction.replace(R.id.fragment_container, mtFragment);*/
                break;
            case R.id.btn_word_value:
                if (!wasFragment) {
                    fragmentTransaction.add(R.id.fragment_container, wdFragment);
                    wasFragment = true;
                }
                else
                    fragmentTransaction.replace(R.id.fragment_container, wdFragment);
                break;
            case R.id.btn_radio_value:
            if (!wasFragment) {
                fragmentTransaction.add(R.id.fragment_container, rfFragment);
                wasFragment = true;
            }
            else
                fragmentTransaction.replace(R.id.fragment_container, rfFragment);
            break;
        }

        fragmentTransaction.commit();

    }
}
