package com.mobility.mvvvmdesignpatterndemo.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created By J7202687 On 10/19/2019
 */

public class NoteRepository {

    private NoteDatabase noteDatabase;
    private NoteDao noteDao;
    LiveData<List<Note>> noteList;

    public NoteRepository(Application application) {
        noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.getNoteDao();
    }

    public LiveData<List<Note>> getNotes() {
        noteList = noteDao.getNoteList();
        return noteList;
    }

    public void insertNote(Note note) {
        noteDao.insertNote(note);
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }
    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }


}
