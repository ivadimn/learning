package ru.ivadimn.contactsbackup.adapters;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataContact;
import ru.ivadimn.contactsbackup.model.RawContact;


/**
 * Created by vadim on 03.05.17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {

    private List<RawContact> persons;

    public ContactsAdapter() {
        //no-op
    }
    public ContactsAdapter(List<RawContact> persons) {
        this.persons = persons;
    }

    public void updateData(List<RawContact> persons) {
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
        private TextView tvId;
        private TextView tvName;


        public ContactHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_photo_id);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_id);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
        }

        public void bind(RawContact p) {
            Bitmap avatar = p.getPhoto();
            if ( avatar == null) {
                image.setImageResource(R.drawable.ic_mood_black_24dp);
            }
            else {
                image.setImageBitmap(avatar);
            }
            tvId.setText(String.valueOf(p.getContactId()));
            tvName.setText(p.getData().getName().getDisplayName());
        }

    }
}
