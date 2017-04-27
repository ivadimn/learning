package ru.ivadimn.notescompanion.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.model.Person;

/**
 * Created by vadim on 27.04.17.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.RVHolder> {

    private List<Person> list;

    public PictureAdapter(List<Person> list) {
        this.list = list;
    }

    public void updateData(List<Person> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public PictureAdapter.RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureAdapter.RVHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class RVHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView tvName;
        private TextView tvPhone;

        public RVHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_photo_id);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_id);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone_id);
        }

        public void bind(Person person) {
            if (person.getPhoto() == 0) {
                image.setImageResource(R.drawable.ic_mood_black_24dp);
            }
            tvName.setText(person.getName());
            tvPhone.setText(person.getPhone());
        }
    }
}
