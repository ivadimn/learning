package ru.ivadimn.notes.ui.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ru.ivadimn.notes.R;
import ru.ivadimn.notes.model.Note;

public class EditActivity extends AppCompatActivity {

    private EditText edTitle;
    private EditText edText;
    private FloatingActionButton fab;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        initUI();
        initData(intent);
    }

    private void initUI() {
        edTitle = (EditText) findViewById(R.id.edit_title_id);
        edText = (EditText) findViewById(R.id.edit_text_id);
        fab = (FloatingActionButton) findViewById(R.id.fab_edit_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(v);
            }
        });
    }

    private void initData(Intent intent) {
        if (intent == null) return;
        edTitle.setText(intent.getStringExtra(Note.TITLE));
        edText.setText(intent.getStringExtra(Note.TEXT));
        index = intent.getIntExtra(Note.INDEX, 0);
    }

    public void saveData(View view) {
        Intent intent = new Intent();
        intent.putExtra(Note.TITLE, edTitle.getText().toString());
        intent.putExtra(Note.TEXT, edText.getText().toString());
        intent.putExtra(Note.INDEX, index);
        setResult(RESULT_OK, intent);
        finish();
    }
}
