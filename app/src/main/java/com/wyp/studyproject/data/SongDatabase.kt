package com.wyp.studyproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 这个数据库有哪些表（可以有多张表）    版本号
@Database(entities = [SongEntity::class],version = 1, exportSchema = true)
abstract class SongDatabase: RoomDatabase() {
    abstract fun getSongDao(): SongDao
}

object SongDatabaseProvider {
    private var INSTANCE: SongDatabase? = null
    fun getDatabase(context: Context):SongDatabase  {
        return INSTANCE ?:synchronized(this) {
            val instance = Room.databaseBuilder(context.applicationContext, SongDatabase::class.java,"song")
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            instance
        }
    }
}







