package com.mobility.mvvvmdesignpatterndemo.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created By J7202687 On 10/19/2019
 */

public class NoteRepository {

    private NoteDatabase noteDatabase;
    public static NoteDao noteDao;
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
        new insertAsyncTask().execute(note);
    }

    public void updateNote(Note note) {
        new updateAsyncTask().execute(note);
    }

    public void deleteNote(Note note) {
        new deleteAsyncTask().execute(note);
    }


    //async task for inserting notes
    public static class insertAsyncTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(notes[0]);
            return null;
        }
    }

    //async task for deleting notes
    public static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteNote(notes[0]);
            return null;
        }
    }

    //async task for updating notes
    public static class updateAsyncTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updateNote(notes[0]);
            return null;
        }
    }

}
