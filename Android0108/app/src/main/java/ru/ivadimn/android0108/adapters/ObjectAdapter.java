package ru.ivadimn.android0108.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.android0108.R;
import ru.ivadimn.android0108.model.ObjectInfo;

/**
 * Created by vadim on 18.06.2017.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ObjectHolder> {

    private List<ObjectInfo> objects;

    public interface OnObjectClickListener {
        public void onItemClick(View view, int position);
    }

    private OnObjectClickListener listener;

    public ObjectAdapter(OnObjectClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<ObjectInfo> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public ObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(ObjectHolder holder, final int position) {
        holder.bind(objects.get(position));
        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }

    class ObjectHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private RelativeLayout layout;
        public ObjectHolder(View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.layout_item_id);
            title = (TextView) itemView.findViewById(R.id.title_id);
            image = (ImageView) itemView.findViewById(R.id.letter_id);
        }

        public RelativeLayout getLayout() {
            return layout;
        }

        public void bind(ObjectInfo info) {
            title.setText(info.getTitle());
            image.setImageResource(info.getImage());
        }
    }
}
