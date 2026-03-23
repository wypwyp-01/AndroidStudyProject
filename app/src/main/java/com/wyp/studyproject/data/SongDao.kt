package com.wyp.studyproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongDao {
    @Insert
    suspend fun insertSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Update
    suspend fun updateSong(song: SongEntity)

    // 加载所有信息
    @Query("select * from SongEntity")
    suspend fun selectAllSong():List<SongEntity>

    @Query("select * from SongEntity where songname=:name order by id desc")
    suspend fun selectSongByName(name: String): List<SongEntity>

    @Query("delete from SongEntity")
    suspend fun deleteAllSong()
}