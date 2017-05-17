package ru.ivadimn.contactsbackup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.ivadimn.contactsbackup.R;

public class MainActivity extends AppCompatActivity {

    private Button btnContacts;
    private Button btnRawContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContacts = (Button) findViewById(R.id.btn_contacts_id);
        btnRawContacts = (Button) findViewById(R.id.btn_rawcontacts_id);
    }

    public void showContacts(View view) {
        Intent intent = new Intent(this, RestoreActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Show Contacts", Toast.LENGTH_SHORT).show();
    }

    public void showRawContacts(View view) {
        Intent intent = new Intent(this, RawContacts.class);
        startActivity(intent);
        //Toast.makeText(this, "Show Raw Contacts", Toast.LENGTH_SHORT).show();
    }
}
