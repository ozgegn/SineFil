package com.ozgegn.sinefil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozgegn.sinefil.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie WHERE id=:id")
    suspend fun get(id: Int): MovieEntity

}