package ru.ivadimn.contactsbackup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.data.WriteProvider;
import ru.ivadimn.contactsbackup.model.Email;
import ru.ivadimn.contactsbackup.model.data.PersonName;
import ru.ivadimn.contactsbackup.model.Phone;
import ru.ivadimn.contactsbackup.model.RawContact;

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

        txtGiven = (EditText) findViewById(R.id.ed_given_id);
        txtFamily = (EditText) findViewById(R.id.ed_family_id);
        txtPhone1 = (EditText) findViewById(R.id.ed_phone1_id);
        txtPhone2 = (EditText) findViewById(R.id.ed_phone2_id);
        txtEmail1 = (EditText) findViewById(R.id.ed_email1_id);
        txtEmail2 = (EditText) findViewById(R.id.ed_email2_id);
        btnRestore = (Button) findViewById(R.id.btn_restore_id);

    }

    public void restore(View view) {
        RawContact rw = new RawContact(null, null);
        String displayName = txtGiven.getText().toString() + " " +
                txtFamily.getText().toString();
        PersonName p = new PersonName();
        p.addValue(PersonName.DISPLAY_NAME, displayName);
        p.addValue(PersonName.GIVEN_NAME,txtGiven.getText().toString());
        p.addValue(PersonName.FAMILY_NAME,txtFamily.getText().toString());
        rw.addElement(p);

        /*rw.getData().addPhone(new Phone(txtPhone1.getText().toString(), "label1", 2));
        rw.getData().addPhone(new Phone(txtPhone2.getText().toString(), "label2", 2));
        rw.getData().addEmail(new Email(txtEmail1.getText().toString(), "email name 1"));
        rw.getData().addEmail(new Email(txtEmail2.getText().toString(), "email name 2"));*/
        WriteProvider wp = new WriteProvider(this);
        wp.writeData(rw);
        //wp.wd();
    }
}
