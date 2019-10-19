package com.mobility.mvvvmdesignpatterndemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobility.mvvvmdesignpatterndemo.R;
import com.mobility.mvvvmdesignpatterndemo.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By J7202687 On 10/19/2019
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyNoteViewHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public MyNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_adapter, null);
        return new MyNoteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyNoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.onBind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class MyNoteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView, descTextView;

        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
        }

        public void onBind(Note note) {
            titleTextView.setText(note.getTitle());
            descTextView.setText(note.getDescription());
        }


    }

}
