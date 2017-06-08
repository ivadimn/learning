package ru.ivadimn.android0105;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText etText;
    private LinearLayout layout;

    private LayoutInflater inflater;
    final int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        etText = (EditText) findViewById(R.id.et_text_id);
        btnAdd = (Button) findViewById(R.id.btn_add_id);
        layout = (LinearLayout) findViewById(R.id.ll_container_id);
        inflater = getLayoutInflater();

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                btnAdd.setEnabled(s.length() > 0);
            }
        });
    }

    public void onClick(View view) {

        View child = inflater.inflate(R.layout.message_item, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(matchParent, 72);
        Resources res = getResources();

        params.setMargins(8, 8, 8, 0);
        child.setLayoutParams(params);
        layout.addView(child);
    }
}
