package com.example.roomapp

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(entities = [RoomNote::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    companion object{
        var instance:NoteDatabase?=null;
        fun getInstance(ctx: Context): NoteDatabase
        {
            if(instance!=null)
            {
                return  instance as NoteDatabase;
            }
            instance= Room.databaseBuilder(ctx,NoteDatabase::class.java,"RoomNotes").run { allowMainThreadQueries() }.build();
            return instance as NoteDatabase;
        }
    }
    abstract fun StudentDao():NoteDao;
}