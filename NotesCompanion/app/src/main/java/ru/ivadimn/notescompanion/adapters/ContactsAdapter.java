package ru.ivadimn.notescompanion.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.model.Person;

/**
 * Created by vadim on 03.05.17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {

    private List<Person> persons;

    public ContactsAdapter(List<Person> persons) {
        this.persons = persons;
    }

    public void updateData(List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.bind(persons.get(position));
    }

    @Override
    public int getItemCount() {
        return persons == null ? 0 : persons.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView tvName;
        private TextView tvPhones;

        public ContactHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_photo_id);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_id);
            tvPhones = (TextView) itemView.findViewById(R.id.tv_phone_id);
        }

        public void bind(Person p) {
            Bitmap avatar = p.getImage();
            if ( avatar == null) {
                image.setImageResource(R.drawable.ic_mood_black_24dp);
            }
            else {
                image.setImageBitmap(avatar);
            }
            tvName.setText(p.getName());
            StringBuilder sb = new StringBuilder();
            for (String s : p.getPhones()) {
                sb.append(s + "\n");
            }
            tvPhones.setText(sb.toString());
        }

    }
}
