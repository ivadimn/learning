package ru.ivadimn.android0106.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    private boolean deleteMode = false;

    public interface PersonClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    private PersonClickListener listener;

    public PersonAdapter(PersonClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item1, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.bind(persons.get(position));
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && !deleteMode)
                    listener.onClick(v, position);
            }
        });
        holder.getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null && !deleteMode)
                    listener.onLongClick(v, position);
                return true;
            }
        });
        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox ch = (CheckBox) v;
                persons.get(position).setDelete(ch.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return persons == null ? 0 : persons.size() ;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name;
        private TextView phone;
        private CardView cardView;
        private CheckBox delete;

        public PersonViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_id);
            photo = (ImageView) itemView.findViewById(R.id.photo_id);
            name = (TextView) itemView.findViewById(R.id.name_id);
            phone = (TextView) itemView.findViewById(R.id.phone_id);
            delete = (CheckBox) itemView.findViewById(R.id.delete_id);
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
            delete.setChecked(p.isDelete());
            delete.setVisibility(deleteMode ? View.VISIBLE : View.INVISIBLE);
        }

        public CheckBox getDelete() {
            return delete;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }
}
