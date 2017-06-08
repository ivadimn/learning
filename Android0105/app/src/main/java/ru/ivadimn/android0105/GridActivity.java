package ru.ivadimn.android0105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GridActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        tv = (TextView) findViewById(R.id.tv_id);
    }


    public void onClick(View view) {
       String text = ((Button) view).getText().toString();
       if (text.equals("C")) {
           tv.setText("");
        }
        else {
           String s = tv.getText().toString();
           tv.setText(s + text);
       }
    }
}
