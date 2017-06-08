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

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText etText;
    private LinearLayout layout;

    private LayoutInflater inflater;

    final int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
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

        TextView tvLogo;
        TextView tvMessage;
        TextView tvTime;

        View child = inflater.inflate(R.layout.message_item, null);
        tvLogo = (TextView) child.findViewById(R.id.tv_logo_id);
        tvMessage = (TextView) child.findViewById(R.id.tv_message_id);
        tvTime = (TextView) child.findViewById(R.id.tv_time_id);

        int height = (int) getResources().getDimension(R.dimen.message_item_size);
        int marginSize = (int) getResources().getDimension(R.dimen.margin_size);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(matchParent, height);
        params.setMargins(marginSize, marginSize, marginSize, 0);
        child.setLayoutParams(params);

        String message = etText.getText().toString();
        tvLogo.setText(message.substring(0, 1).toUpperCase());
        tvMessage.setText(message);
        tvTime.setText(format.format(new Date()));
        layout.addView(child);
    }
}
