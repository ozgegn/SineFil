package com.ozgegn.sinefil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozgegn.sinefil.data.local.entity.GenreEntity

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(genreEntity: GenreEntity)

    @Query("SELECT * from genre")
    suspend fun getAll(): List<GenreEntity>
}