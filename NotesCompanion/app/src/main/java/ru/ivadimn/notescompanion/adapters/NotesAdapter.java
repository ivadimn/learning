package ru.ivadimn.notescompanion.adapters;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.interfaces.Listener;
import ru.ivadimn.notescompanion.interfaces.LongListener;
import ru.ivadimn.notescompanion.model.Note;

/**
 * Created by vadim on 12.03.17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    public static final String TAG = "NOTE_ADAPTER";
    public static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_DELETE = 1;

    private List<Note> notes;
    private Listener listener;
    private LongListener longListener;
    private boolean deleteMode = false;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public void update(List<Note> notes, Cursor cursor) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_DELETE)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_delete, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        Log.d(TAG, "onCreateViewHolder - deleteMode = " + deleteMode);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, final int position) {
        holder.bind(notes.get(position));
        Log.d(TAG, "onBindViewHolder - deleteMode = " + deleteMode);
        CheckBox ch = holder.getChDelete();
        if (ch != null)
            ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCheckBoxClick(v, position);
                }
            });
        holder.getTvNote().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTextClick(v, position);
            }
        });
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCardClick(v, position);
            }
        });
        holder.getTvNote().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longListener.onClick(position);
                Log.d("TAG", "Long click listener");
                return true;
            }
        });
        holder.getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longListener.onClick(position);
                Log.d("TAG", "Long click listener");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (deleteMode)
            return VIEW_TYPE_DELETE;
        else
            return VIEW_TYPE_NORMAL;
    }

    public  void setDeleteMode(boolean mode) {
        this.deleteMode = mode;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setLongListener(LongListener longListener) {
        this.longListener = longListener;
    }

    /////////////////////////////////////////////////////////////////////////////////
    class NoteHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTitle;
        TextView tvNote;
        TextView tvDateTime;
        CheckBox chDelete = null;

        public NoteHolder(View itemView) {
            super(itemView);
            if (deleteMode) {
                chDelete = (CheckBox) itemView.findViewById(R.id.ch_ready_todelete_id);

            }
            cardView = (CardView) itemView.findViewById(R.id.cv_note_id);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_id);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note_id);
            tvDateTime = (TextView) itemView.findViewById(R.id.tv_datetime_id);
        }

        public void bind(Note note) {
            tvTitle.setText(note.getTitle());
            tvNote.setText(note.getContent());
            tvDateTime.setText(note.getMomentString());
        }

        public CheckBox getChDelete() {
            return chDelete;
        }

        public TextView getTvNote() {
            return tvNote;
        }

        public CardView getCardView() {
            return cardView;
        }
    }
}
