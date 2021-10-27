package com.example.roomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoomNotes")
class RoomNote(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "pk") val pk: Int = 0,
    @ColumnInfo(name = "note") val note: String
)