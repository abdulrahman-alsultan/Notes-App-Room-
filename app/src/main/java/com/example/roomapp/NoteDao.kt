package com.example.roomapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM RoomNotes")
    fun getAllNotes(): MutableList<RoomNote>

    @Insert
    fun addNote(note: RoomNote)

    @Query("DELETE FROM RoomNotes WHERE pk =:pk")
    fun deleteNote(pk: Int)

    @Query("UPDATE RoomNotes SET note = :note WHERE pk =:pk")
    fun updateNote(pk: Int, note: String)
}