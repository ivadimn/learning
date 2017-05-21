package ru.ivadimn.contactsbackup.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.ivadimn.contactsbackup.R;
import ru.ivadimn.contactsbackup.model.DataElementDisplay;

/**
 * Created by vadim on 20.05.2017.
 */

public class ContactDetailAdapter extends RecyclerView.Adapter<ContactDetailAdapter.ElementHolder> {

    private ArrayList<DataElementDisplay> elements;

    public ContactDetailAdapter(ArrayList<DataElementDisplay> elements) {
        this.elements = elements;
    }

    @Override
    public ElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list_item, parent, false);
        return new ElementHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementHolder holder, int position) {
        holder.bind(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements == null ? 0 : elements.size();
    }

    public class ElementHolder extends RecyclerView.ViewHolder {

        private TextView tvMain;
        private TextView tvType;
        private TextView tvDop;

        public ElementHolder(View itemView) {
            super(itemView);
            tvMain = (TextView) itemView.findViewById(R.id.tv_main_id);
            tvType = (TextView) itemView.findViewById(R.id.tv_type_id);
            tvDop = (TextView) itemView.findViewById(R.id.tv_dop_id);
        }

        public void bind(DataElementDisplay dd) {
            String[] vals = dd.getData();
            if (vals == null) return;
            int index = 0;
            tvMain.setText(vals[index++]);
            if (index < vals.length)
                tvType.setText(vals[index++]);
            if (index < vals.length)
                tvDop.setText(vals[index]);

        }
    }
}
