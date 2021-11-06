package com.ozgegn.sinefil.data

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Result<List<MovieModel>>

    suspend fun getNowPlayingMovies(page: Int): Result<List<MovieModel>>

    suspend fun getTopRatedMovies(page: Int): Result<List<MovieModel>>

    suspend fun getGenres(): Result<List<GenreModel>>

    suspend fun saveMovie(movieModel: MovieModel)

    suspend fun getMovie(id: Int): Result<MovieModel>

    suspend fun getSearchResults(genreId: Int): Result<List<MovieModel>>

}