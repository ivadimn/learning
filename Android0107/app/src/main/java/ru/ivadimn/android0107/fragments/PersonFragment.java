package ru.ivadimn.android0107.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.ivadimn.android0107.App;
import ru.ivadimn.android0107.R;
import ru.ivadimn.android0107.model.Person;

import static android.app.Activity.RESULT_OK;

/**
 * Created by vadim on 15.06.17.
 */

public class PersonFragment extends Fragment {

    public static final String TAG = "PERSON";

    public static final int VIEW = 0;
    public static final int ADD = 1;
    public static final int REQUEST_PHOTO = 2;

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

    private OnPersonFragmentListener listener;


    public interface OnPersonFragmentListener {
        public Person getSelectedPerson();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPersonFragmentListener)
            listener = (OnPersonFragmentListener) context;
        else
            throw new RuntimeException("context must implement OnSelectItemListener interface");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        initUI(view);
        return view;
    }


    private void initUI(View v) {
        photo = (ImageView) v.findViewById(R.id.person_photo_id);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditPhoto) getPhoto();
            }
        });
        name = (EditText) v.findViewById(R.id.person_name_id);
        phone = (EditText) v.findViewById(R.id.person_phone_id);
        email = (EditText) v.findViewById(R.id.person_email_id);
        hobbys = (EditText) v.findViewById(R.id.person_hobby_id);
        photo.setImageResource(R.drawable.person_big);
    }

    public void initData(int m) {
        clearViews();
        setEditEnable(false);
        Person p = listener.getSelectedPerson();
        mode = m;
        if (mode == ADD) {
            photo.setImageResource(R.drawable.person_big);
            setEditEnable(true);
            return;
        }

        Bitmap bmp = p.getPhoto();
        if (bmp != null) {
            photo.setImageBitmap(bmp);
        }
        else {
            photo.setImageResource(R.drawable.person_big);
        }
        name.setText(p.getName());
        phone.setText(p.getPhone());
        email.setText(p.getEmail());
        hobbys.setText(p.getHobbys());
    }


    private void getPhoto() {
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, )
        //startActivityForResult(intent, REQUEST_PHOTO);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PHOTO);
    }

    public void setEditEnable(boolean enable) {
        if (!enable)
            clearViews();
        isEditPhoto = enable;
        name.setEnabled(enable);
        phone.setEnabled(enable);
        email.setEnabled(enable);
        hobbys.setEnabled(enable);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_PHOTO) {
            //Bundle bundle = data.getExtras();
            Uri uri = data.getData();
            try {

                bmpPhoto = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                //bmpPhoto = (Bitmap) bundle.get("data");
                photo.setImageBitmap(bmpPhoto);
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();;
            }
        }
    }

    public Person getData() {
        Person p = new Person(name.getText().toString(), phone.getText().toString(),
                email.getText().toString(), hobbys.getText().toString());
        if (bmpPhoto != null) {
            p.setPhoto(bmpPhoto);
        }
        clearViews();
        setEditEnable(false);
        return p;
    }

    public void clearViews() {
        name.setText("");
        phone.setText("");
        email.setText("");
        hobbys.setText("");
        photo.setImageResource(R.drawable.person_big);
    }
}
