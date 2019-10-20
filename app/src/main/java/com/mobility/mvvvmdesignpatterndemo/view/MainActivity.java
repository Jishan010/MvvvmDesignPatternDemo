package com.mobility.mvvvmdesignpatterndemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobility.mvvvmdesignpatterndemo.R;
import com.mobility.mvvvmdesignpatterndemo.adapters.NoteListAdapter;
import com.mobility.mvvvmdesignpatterndemo.model.Note;
import com.mobility.mvvvmdesignpatterndemo.viewmodel.NoteViewModel;

import java.util.List;

import static com.mobility.mvvvmdesignpatterndemo.Utility.DESCRIPTION;
import static com.mobility.mvvvmdesignpatterndemo.Utility.ID;
import static com.mobility.mvvvmdesignpatterndemo.Utility.TITLE;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private NoteListAdapter noteListAdapter;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.notes_title));
        setSupportActionBar(toolbar);
        initRecycleView();
        noteListAdapter = new NoteListAdapter();
        recyclerView.setAdapter(noteListAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNoteList().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesList = notes;
                noteListAdapter.submitList(notesList);
            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        //swipe to delete functionality starts from here
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.deleteNote(noteListAdapter.getNotes(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);


        //updating the item on click event
        noteListAdapter.setOnItemClickListener(new NoteListAdapter.OnItemClickListener() {
            @Override
            public void updateNote(Note note) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                intent.putExtra(ID, note.getNoteId());
                intent.putExtra(TITLE, note.getTitle());
                intent.putExtra(DESCRIPTION, note.getDescription());
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(TITLE);
                String description = data.getStringExtra(DESCRIPTION);
                Note note = new Note(title, description);
                noteViewModel.insertNote(note);
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(TITLE);
                String description = data.getStringExtra(DESCRIPTION);
                Note note = new Note(title, description);
                int noteId = data.getIntExtra(ID, -1);
                if (noteId != -1) {
                    note.setNoteId(noteId);
                }
                noteViewModel.updateNote(note);
            }
        }
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }
}
