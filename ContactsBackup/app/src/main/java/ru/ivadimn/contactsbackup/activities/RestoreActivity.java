package ru.ivadimn.contactsbackup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.Element;

public class RestoreActivity extends AppCompatActivity {

    private EditText txtFamily;
    private EditText txtGiven;
    private EditText txtPhone1;
    private EditText txtPhone2;
    private EditText txtEmail1;
    private EditText txtEmail2;
    private Button btnRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore);

        txtGiven = (EditText) findViewById(R.id.tv_given_id);
        txtFamily = (EditText) findViewById(R.id.tv_family_id);
        txtPhone1 = (EditText) findViewById(R.id.tv_phone1_id);
        txtPhone2 = (EditText) findViewById(R.id.tv_phone2_id);
        txtEmail1 = (EditText) findViewById(R.id.tv_email1_id);
        txtEmail2 = (EditText) findViewById(R.id.tv_email2_id);
        btnRestore = (Button) findViewById(R.id.btn_restore_id);

    }

    public void restore(View view) {
        DataContact data = new DataContact();
        Element e = new Element(DataContact.PHONE_TYPE);
    }
}
