package ru.ivadimn.android0106.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import ru.ivadimn.android0106.R;
import ru.ivadimn.android0106.model.Person;

public class PersonActivity extends AppCompatActivity {

    public static final int VIEW = 0;
    public static final int ADD = 1;
    public static final int REQUEST_PHOTO = 2;
    public static final String INDEX = "INDEX";

    private static final String PERSON_PHOTO = "PERSON_PHOTO";
    private static final String PERSON_NAME = "PERSON_NAME";
    private static final String PERSON_PHONE = "PERSON_PHONE";
    private static final String PERSON_EMAIL = "PERSON_EMAIL";
    private static final String PERSON_HOBBYS = "PERSON_HOBBYS";
    private static final String MODE = "MODE";


    private ImageView photo;
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText hobbys;
    private Bitmap bmpPhoto = null;
    private boolean isEditPhoto = false;
    private MenuItem itemSave;
    private MenuItem itemEdit;
    private int index;
    private int mode;

    public static Intent createIntent(Context context, Person p, int position) {
        Intent intent = new Intent(context, PersonActivity.class);
        if (p == null) {
            intent.putExtra(MODE, ADD);
            return intent;
        }
        intent.putExtra(MODE, VIEW);
        intent.putExtra(INDEX, position);
        Bitmap bmp = p.getPhoto();
        if (bmp != null) {
            byte[] buffer = getPhotoByteArray(bmp);
            intent.putExtra(PERSON_PHOTO, buffer);
        }
        intent.putExtra(PERSON_NAME, p.getName());
        intent.putExtra(PERSON_PHONE, p.getPhone());
        intent.putExtra(PERSON_EMAIL, p.getEmail());
        intent.putExtra(PERSON_HOBBYS, p.getHobbys());
        return intent;
    }

    public static Person getPerson(Intent intent) {
        Person p = new Person();
        p.setName(intent.getStringExtra(PERSON_NAME));
        p.setPhone(intent.getStringExtra(PERSON_PHONE));
        p.setEmail(intent.getStringExtra(PERSON_EMAIL));
        p.setHobbys(intent.getStringExtra(PERSON_HOBBYS));
        byte[] buffer = intent.getByteArrayExtra(PERSON_PHOTO);
        if (buffer != null) {
            p.setPhoto(BitmapFactory.decodeByteArray(buffer, 0, buffer.length));
        }
        return p;
    }

    private static byte[] getPhotoByteArray(Bitmap bmp) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, out);
        return out.toByteArray();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        initUI();
        initData(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.person_menu, menu);
        itemSave = menu.findItem(R.id.item_save_id);
        itemEdit = menu.findItem(R.id.item_edit_id);
        if (mode == ADD) {
            itemEdit.setVisible(false);
            itemSave.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_edit_id:
                setEditEnable();
                itemEdit.setVisible(false);
                itemSave.setVisible(true);
                break;
            case R.id.item_save_id:
                saveData();
                break;
        }
        return true;
    }

    private void initUI() {
        photo = (ImageView) findViewById(R.id.person_photo_id);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditPhoto) getPhoto();
            }
        });

        name = (EditText) findViewById(R.id.person_name_id);
        phone = (EditText) findViewById(R.id.person_phone_id);
        email = (EditText) findViewById(R.id.person_email_id);
        hobbys = (EditText) findViewById(R.id.person_hobby_id);
    }

    private void initData(Intent intent) {
        mode = intent.getIntExtra(MODE, VIEW);
        if (mode == ADD) {
            photo.setImageResource(R.drawable.person_big);
            setEditEnable();

            return;
        }
        index = intent.getIntExtra(INDEX, -1);
        byte[] image  = intent.getByteArrayExtra(PERSON_PHOTO);
        if (image != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            photo.setImageBitmap(bmp);
        }
        else {
            photo.setImageResource(R.drawable.person_big);
        }
        name.setText(intent.getStringExtra(PERSON_NAME));
        phone.setText(intent.getStringExtra(PERSON_PHONE));
        email.setText(intent.getStringExtra(PERSON_EMAIL));
        hobbys.setText(intent.getStringExtra(PERSON_HOBBYS));
    }

    private void setEditEnable() {
        isEditPhoto = true;
        name.setEnabled(true);
        phone.setEnabled(true);
        email.setEnabled(true);
        hobbys.setEnabled(true);
    }

    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_PHOTO) {
            Uri uri = data.getData();
            try {
                bmpPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                photo.setImageBitmap(bmpPhoto);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
            }
        }
    }

    private void saveData() {
        Intent intent = new Intent();
        intent.putExtra(INDEX, index);
        if (bmpPhoto != null) {
            byte[] b = getPhotoByteArray(bmpPhoto);
            intent.putExtra(PERSON_PHOTO, b);
        }
        intent.putExtra(PERSON_NAME, name.getText().toString());
        intent.putExtra(PERSON_PHONE, phone.getText().toString());
        intent.putExtra(PERSON_EMAIL, email.getText().toString());
        intent.putExtra(PERSON_HOBBYS, hobbys.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


}
