package com.wyp.studyproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SongEntity")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val songname: String,
    val singer: String,
    val time: Long
)
