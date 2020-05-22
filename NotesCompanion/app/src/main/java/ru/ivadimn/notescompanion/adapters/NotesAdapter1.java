package ru.ivadimn.notescompanion.adapters;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ru.ivadimn.notescompanion.R;
import ru.ivadimn.notescompanion.interfaces.Listener;
import ru.ivadimn.notescompanion.interfaces.LongListener;
import ru.ivadimn.notescompanion.model.Note;
import ru.ivadimn.notescompanion.model.NoteProviderMetaData;
import ru.ivadimn.notescompanion.model.OrganizerContract;

/**
 * Created by vadim on 02.04.2017.
 */

public class NotesAdapter1 extends RecyclerView.Adapter<NotesAdapter1.ItemHolder> {

    public static final String TAG = "NOTE_ADAPTER";
    public static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_DELETE = 1;

    private Context context;
    private Listener listener;
    private LongListener longListener;
    private LayoutInflater inflater;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private boolean deleteMode = false;
    private Cursor cursor;

    public NotesAdapter1(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setLongListener(LongListener longListener) {
        this.longListener = longListener;
    }

    public void update(Cursor cursor) {
        this.cursor = cursor;
        if (cursor != null) {
            cursor.registerContentObserver(observer);
        }
        notifyDataSetChanged();
    }

    public  boolean isDeleteMode() {
        return deleteMode;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_DELETE)
            view = inflater.inflate(R.layout.note_item_delete, parent, false);
        else
            view = inflater.inflate(R.layout.note_item, parent, false);

        Log.d(TAG, "onCreateViewHolder - deleteMode = " + deleteMode);
        return new NotesAdapter1.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        if(!cursor.moveToPosition(position)) return;
        String title = cursor.getString(cursor.getColumnIndex(OrganizerContract.Notes.TITLE));
        String ntext = cursor.getString(cursor.getColumnIndex(OrganizerContract.Notes.NTEXT));
        long moment = cursor.getLong(cursor.getColumnIndex(OrganizerContract.Notes.MOMENT));
        String strMoment = format.format(moment);
        holder.bind(title, ntext, strMoment);
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
    }

    @Override
    public int getItemCount() {
        return (cursor == null) ? 0 : cursor.getCount();
    }

    /////////////////////////////////////////////////////////////////////////////////
    class ItemHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTitle;
        TextView tvNote;
        TextView tvDateTime;
        CheckBox chDelete = null;

        public ItemHolder(View itemView) {
            super(itemView);
            if (deleteMode) {
                chDelete = (CheckBox) itemView.findViewById(R.id.ch_ready_todelete_id);

            }
            cardView = (CardView) itemView.findViewById(R.id.cv_note_id);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_id);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note_id);
            tvDateTime = (TextView) itemView.findViewById(R.id.tv_datetime_id);
        }

        public void bind(String title, String ntext, String moment) {
            tvTitle.setText(title);
            tvNote.setText(ntext);
            tvDateTime.setText(moment);
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

    private ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            notifyDataSetChanged();
            Log.d(TAG, "Provider onChanged");
        }
    };
}
