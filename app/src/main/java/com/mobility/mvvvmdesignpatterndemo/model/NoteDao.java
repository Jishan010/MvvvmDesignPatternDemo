package com.mobility.mvvvmdesignpatterndemo.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created By J7202687 On 10/19/2019
 */

@Dao
interface NoteDao {

    @Query("select * from Note")
    LiveData<List<Note>> getNoteList();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
