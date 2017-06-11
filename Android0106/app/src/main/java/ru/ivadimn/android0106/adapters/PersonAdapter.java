package ru.ivadimn.android0106.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.android0106.R;
import ru.ivadimn.android0106.model.Person;

/**
 * Created by vadim on 11.06.2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> persons;


    public void updateData(List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.bind(persons.get(position));
    }

    @Override
    public int getItemCount() {
        return persons == null ? 0 : persons.size() ;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name;
        private TextView phone;

        public PersonViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo_id);
            name = (TextView) itemView.findViewById(R.id.name_id);
            phone = (TextView) itemView.findViewById(R.id.phone_id);

        }

        public void bind(Person p) {
            Bitmap bmp = p.getPhoto();
            if (bmp == null) {
                photo.setImageResource(R.drawable.person_small);
            }
            else {
                photo.setImageBitmap(bmp);
            }
            name.setText(p.getName());
            phone.setText(p.getPhone());
        }
    }
}
