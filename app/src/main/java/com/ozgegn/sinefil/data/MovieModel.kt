package com.ozgegn.sinefil.data

data class MovieModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) {
    fun getBackDropUrl(): String = "https://image.tmdb.org/t/p/w500/$backdrop_path"

    fun getOriginalPosterUrl(): String = "https://image.tmdb.org/t/p/original/$poster_path"

}