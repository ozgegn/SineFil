package com.ozgegn.sinefil.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "backdropPath") val backdrop_path: String?,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "originalLanguage") val original_language: String,
    @ColumnInfo(name = "originalTitle") val original_title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "posterPath") val poster_path: String?,
    @ColumnInfo(name = "releaseDate") val release_date: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "voteAverage") val vote_average: Double,
    @ColumnInfo(name = "voteCount") val vote_count: Int,
    @ColumnInfo(name = "added_to_watchlist") val added_to_watchlist: Boolean = false
)