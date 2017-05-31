package ru.ivadimn.android0103.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.ivadimn.android0103.R;

import static android.content.Intent.ACTION_SEND;

/**
 * Created by vadim on 31.05.17.
 */

public class EmailActivity extends Activity {

    private EditText emailAddress;
    private EditText subject;
    private EditText content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        initUI();
    }

    private void initUI() {
        emailAddress = (EditText) findViewById(R.id.et_email_id);
        subject = (EditText)findViewById(R.id.et_subject_id);
        content = (EditText) findViewById(R.id.et_content_id);
    }

    public void onClick(View view) {
        sendEmail();
    }


    private void sendEmail() {
        Intent intent = new Intent(ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());
        intent.setType("plain/text");
        try {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex) {
            Toast.makeText(this, "Email клиент не установлен", Toast.LENGTH_SHORT).show();
        }
    }


}
