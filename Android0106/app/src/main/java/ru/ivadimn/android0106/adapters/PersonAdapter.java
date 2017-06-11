package ru.ivadimn.android0106.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.ivadimn.android0106.model.Person;

/**
 * Created by vadim on 11.06.2017.
 */

public class PersonAdapter  {


    class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name;
        private TextView phone;

        public PersonViewHolder(View itemView) {
            super(itemView);
            photo

        }

        public void bind(Person p) {

        }
    }
}
