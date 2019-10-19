package com.mobility.mvvvmdesignpatterndemo.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created By J7202687 On 10/19/2019
 */

@Database(entities = {Note.class} , version = 1)
public abstract class NoteDatabase extends RoomDatabase{

    private static volatile  NoteDatabase noteDatabaseInstance;
    public abstract NoteDao getNoteDao();

    static NoteDatabase getInstance(Context context) {
        if (noteDatabaseInstance == null) {
            noteDatabaseInstance = Room.databaseBuilder(context, NoteDatabase.class, "NoteDatabase").build();
        }
        return noteDatabaseInstance;
    }

    public void cleanUp() {
        noteDatabaseInstance = null;
    }


}
