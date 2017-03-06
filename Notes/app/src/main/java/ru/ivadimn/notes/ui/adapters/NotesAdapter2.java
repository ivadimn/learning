package ru.ivadimn.notes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.notes.R;
import ru.ivadimn.notes.model.Note;
import ru.ivadimn.notes.model.Note1;

/**
 * Created by vadim on 06.03.17.
 */

public class NotesAdapter2 extends BaseAdapter  {

    private Context context;
    private LayoutInflater inflater;
    private List<Note1> notes;
    private int selectedColor;
    private int fonColor;


    public NotesAdapter2(Context context, List<Note1> notes) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
        selectedColor = context.getColor(R.color.colorAccent);
        fonColor = context.getColor(R.color.card_background_color);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        NotesAdapter1.ViewHolder holder;
        Note1 note = notes.get(i);
        if (v == null) {
            v = inflater.inflate(R.layout.note_item, null, true);
            holder = new NotesAdapter1.ViewHolder();
            holder.tvTitle = (TextView) v.findViewById(R.id.tv_title_id);
            holder.tvDateTime = (TextView) v.findViewById(R.id.tv_datetime_id);

            v.setTag(holder);
        }
        else {
            holder = (NotesAdapter1.ViewHolder) v.getTag();
        }

        holder.tvTitle.setText(note.getTitle());
        holder.tvDateTime.setText(note.getMomentString());
        if (note.isChecked())
            v.setBackgroundColor(selectedColor);
        else
            v.setBackgroundColor(fonColor);
        return v;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvDateTime;
    }
}
