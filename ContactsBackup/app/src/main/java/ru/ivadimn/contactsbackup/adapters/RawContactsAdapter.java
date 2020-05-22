package ru.ivadimn.contactsbackup.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.RawContact;

/**
 * Created by vadim on 14.05.2017.
 */

public class RawContactsAdapter extends RecyclerView.Adapter<RawContactsAdapter.RawContactsHolder> {

    List<RawContact> list;

    public RawContactsAdapter() {

    }

    public void updateData(List<RawContact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RawContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_contact_item, parent, false);
        return new RawContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(RawContactsHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RawContactsHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvContactId;
        private TextView tvName;
        private TextView tvType;
        private TextView tvRing;
        public RawContactsHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tv_id_id);
            tvContactId = (TextView) view.findViewById(R.id.tv_contactid_id);
            tvName = (TextView) view.findViewById(R.id.tv_name_id);
            tvType = (TextView) view.findViewById(R.id.tv_type_id);
            tvRing = (TextView) view.findViewById(R.id.tv_ring_id);
        }

        public void bind(RawContact rc) {
            tvId.setText(String.valueOf(rc.get_id()));
            tvContactId.setText(String.valueOf(rc.getContactId()));
            tvName.setText(rc.getAccountName());
            tvType.setText(rc.getAccountType());
            tvRing.setText(rc.getCustomRingtone());
        }

        
    }
}
