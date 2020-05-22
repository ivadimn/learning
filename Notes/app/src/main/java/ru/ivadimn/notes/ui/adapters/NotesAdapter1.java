package ru.ivadimn.notes.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.notes.R;
import ru.ivadimn.notes.model.Note;

/**
 * Created by vadim on 01.03.17.
 */

public class NotesAdapter1 extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Note> notes;
    private int selectedColor;
    private int fonColor;


    public NotesAdapter1(Context context, List<Note> notes) {
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
        ViewHolder holder;
        Note note = notes.get(i);
        if (v == null) {
            v = inflater.inflate(R.layout.note_item, null, true);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) v.findViewById(R.id.tv_title_id);
            holder.tvDateTime = (TextView) v.findViewById(R.id.tv_datetime_id);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
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
