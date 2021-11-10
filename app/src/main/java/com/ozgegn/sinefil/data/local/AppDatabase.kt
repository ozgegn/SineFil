package com.ozgegn.sinefil.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozgegn.sinefil.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}