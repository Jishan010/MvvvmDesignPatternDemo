package com.mobility.mvvvmdesignpatterndemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mobility.mvvvmdesignpatterndemo.R;
import com.mobility.mvvvmdesignpatterndemo.model.Note;

/**
 * Created By J7202687 On 10/19/2019
 */


/**
 * here we're using listAdapter abstract class which is base class of recyclerView
 * to make use of awesome api diffutil which enables add and remove animation on list item notify data set change
 * */
public class NoteListAdapter extends ListAdapter<Note, NoteListAdapter.MyNoteViewHolder> {

//    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public NoteListAdapter() {
        super(diffCallback);
    }

    private static final DiffUtil.ItemCallback<Note> diffCallback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNoteId() == newItem.getNoteId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public MyNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_adapter, null);
        return new MyNoteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyNoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.onBind(note);
    }


    /**
     We don't need getItemCount method  while using listadapter of recycler view
     */

 /*   @Override
    public int getItemCount() {
        return notes.size();
    }
    */

    /**
     We don't need setNotes method  while using listadapter of recycler view ..instead we can use $submitList()
     */
 /*
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }*/

    public Note getNotes(int position) {
        Note note = getItem(position);
        return note;
    }


    public class MyNoteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView, descTextView;

        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.updateNote(getNotes(getAdapterPosition()));
                }
            });
        }

        public void onBind(Note note) {
            titleTextView.setText(note.getTitle());
            descTextView.setText(note.getDescription());
        }
    }

    public interface OnItemClickListener {
        void updateNote(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
