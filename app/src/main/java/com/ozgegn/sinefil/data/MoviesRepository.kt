package com.ozgegn.sinefil.data

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Result<List<MovieModel>>

    suspend fun getNowPlayingMovies(page: Int): Result<List<MovieModel>>

    suspend fun getTopRatedMovies(page: Int): Result<List<MovieModel>>

    suspend fun getGenres(): Result<List<GenreModel>>

}