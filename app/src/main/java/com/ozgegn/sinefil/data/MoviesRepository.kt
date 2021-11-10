package com.ozgegn.sinefil.data

import androidx.paging.PagingData
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getNowPlayingMovies(): Flow<PagingData<MovieResponseModel>?>

    suspend fun getGenres(): Result<List<GenreModel>>

    suspend fun saveMovie(movieModel: MovieModel)

    suspend fun getMovie(id: Int): Result<MovieModel>

    suspend fun getSearchResults(genreId: Int): Result<List<MovieModel>>

    suspend fun getWatchList(): Result<List<MovieModel>>

}