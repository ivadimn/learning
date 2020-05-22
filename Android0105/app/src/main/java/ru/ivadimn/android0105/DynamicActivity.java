package ru.ivadimn.android0105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText etText;
    private LinearLayout layout;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        etText = (EditText) findViewById(R.id.et_text_id);
        btnAdd = (Button) findViewById(R.id.btn_add_id);
        layout = (LinearLayout) findViewById(R.id.ll_container_id);

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

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                matchParent, wrapContent);
        int marginSize = (int) getResources().getDimension(R.dimen.margin_size);
        lParams.setMargins(marginSize, marginSize, marginSize, 0);
        TextView textView = new TextView(this);
        textView.setTextSize(getResources().getDimension(R.dimen.text_title_size));
        textView.setText(etText.getText().toString());
        layout.addView(textView, lParams);
    }
}
