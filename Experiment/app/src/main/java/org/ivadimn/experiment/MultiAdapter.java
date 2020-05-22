package org.ivadimn.experiment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by vadim on 25.06.16.
 */
public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiHolder> {

    public static final String TAG = "MULTI_ADAPTER";
    public static int count = 0;

    List<Upshot> list;

    public MultiAdapter(List<Upshot> list) {
        this.list = list;
    }

    @Override
    public MultiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_value_item, parent, false);
        Log.d(TAG, ++count + " OnCreateViewHolder");
        return new MultiHolder(view);
    }

    @Override
    public void onBindViewHolder(MultiHolder holder, int position) {
        Log.d(TAG, ++count + " OnBindViewHolder");
        holder.bindHolder(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, ++count + " getItemCount");
        return list == null ? 0 : list.size();
    }

    public class MultiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private CheckBox checkBox;
        //TextView textView;
        private Context context;
        Upshot item;

        public MultiHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            context = itemView.getContext();
            cardView = (CardView) itemView.findViewById(R.id.cv_multi_reply_id);
            checkBox = (CheckBox) itemView.findViewById(R.id.multi_reply_id);
            checkBox.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, ++count + " OnClick");
            Toast.makeText(context, v.toString(), Toast.LENGTH_SHORT).show();
        }

        public void bindHolder(Upshot item) {
            this.item = item;
            checkBox.setText(item.getTitle());
            checkBox.setChecked(item.isValue());
        }
    }
}
