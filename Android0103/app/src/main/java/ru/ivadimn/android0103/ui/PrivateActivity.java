package ru.ivadimn.android0103.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ru.ivadimn.android0103.R;
import ru.ivadimn.android0103.model.Person;

public class PrivateActivity extends Activity {

    private static final String PERSON_NAME = "PERSON_NAME";
    private static final String PERSON_PHONE = "PERSON_PHONE";
    private static final String PERSON_EMAIL = "PERSON_EMAIL";

    public static Intent createIntent(Context context, Person p) {
        Intent intent = new Intent(context, PrivateActivity.class);
        intent.putExtra(PERSON_NAME, p.getName());
        intent.putExtra(PERSON_PHONE, p.getPhone());
        intent.putExtra(PERSON_EMAIL, p.getEmail());
        return intent;
    }

    public static Person getPerson(Intent data) {
        return new Person(data.getStringExtra(PERSON_NAME),
                data.getStringExtra(PERSON_PHONE),
                data.getStringExtra(PERSON_EMAIL));
    }

    private EditText edName;
    private EditText edPhone;
    private EditText edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
        Intent intent = getIntent();
        initUI();
        initData(intent);
    }

    private void initUI() {
        edName = (EditText) findViewById(R.id.et_person_name_id);
        edPhone = (EditText) findViewById(R.id.et_person_phone_id);
        edEmail = (EditText) findViewById(R.id.et_person_email_id);
    }

    private void initData(Intent data) {
        edName.setText(data.getStringExtra(PERSON_NAME));
        edPhone.setText(data.getStringExtra(PERSON_PHONE));
        edEmail.setText(data.getStringExtra(PERSON_EMAIL));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_save_id) return;
        Intent intent = new Intent();
        intent.putExtra(PERSON_NAME, edName.getText().toString());
        intent.putExtra(PERSON_PHONE, edPhone.getText().toString());
        intent.putExtra(PERSON_EMAIL, edEmail.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
