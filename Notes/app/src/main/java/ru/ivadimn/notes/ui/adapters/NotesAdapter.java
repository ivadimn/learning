package ru.ivadimn.notes.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.notes.R;
import ru.ivadimn.notes.model.Note;

/**
 * Created by vadim on 27.02.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder>  {

    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDateTime;

        public NoteHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_id);
            tvDateTime = (TextView) itemView.findViewById(R.id.tv_datetime_id);
        }

        public void bind(Note note) {
            tvTitle.setText(note.getTitle());
            tvDateTime.setText(note.getMomentString());
        }
    }
}
