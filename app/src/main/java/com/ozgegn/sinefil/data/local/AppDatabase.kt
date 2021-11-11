package com.ozgegn.sinefil.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozgegn.sinefil.data.local.entity.GenreEntity
import com.ozgegn.sinefil.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, GenreEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}