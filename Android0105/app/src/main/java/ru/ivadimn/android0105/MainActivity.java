package ru.ivadimn.android0105;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_dynamic_id:
                startDynamicActivity(DynamicActivity.class);
                break;
            case R.id.btn_message_id:
                startDynamicActivity(MessageActivity.class);
                break;
        }
    }

    private void startDynamicActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
