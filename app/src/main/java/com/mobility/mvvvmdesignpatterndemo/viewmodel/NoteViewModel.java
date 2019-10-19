package com.mobility.mvvvmdesignpatterndemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobility.mvvvmdesignpatterndemo.model.Note;
import com.mobility.mvvvmdesignpatterndemo.model.NoteRepository;

import java.util.List;

/**
 * Created By J7202687 On 10/19/2019
 */

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> noteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        noteList = noteRepository.getNotes();
    }

    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }

    public void insertNote(Note note) {
        noteRepository.insertNote(note);
    }

    public void updateNote(Note note) {
        noteRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNote(note);
    }


}
