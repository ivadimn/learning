package ru.ivadimn.android0108.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ru.ivadimn.android0108.App;
import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.model.ObjectInfo;

public class DescriptionActivity extends AppCompatActivity {

    public static final String TAG = "DescriptionActivity";

    private ImageView imageView;
    private TextView txtDescription;
    private TextView txtName;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        initUI();
    }

    private void initUI() {
        Intent intent = getIntent();
        imageView = (ImageView) findViewById(R.id.image_id);
        int resId = intent.getIntExtra(ObjectInfo.TAG_IMAGE, 0);
        imageView.setImageResource(resId);
        txtName = (TextView) findViewById(R.id.text_name_id);
        txtName.setText(intent.getStringExtra(ObjectInfo.TAG_NAME));
        txtDescription = (TextView) findViewById(R.id.text_description_id);
        txtDescription.setText(intent.getStringExtra(ObjectInfo.TAG_DESCRIPTION));
    }
}
