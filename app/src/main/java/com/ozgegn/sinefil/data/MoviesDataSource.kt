package com.ozgegn.sinefil.data

import androidx.paging.PagingData
import com.ozgegn.sinefil.data.local.entity.MovieEntity
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {

    interface RemoteDataSource {

        fun getNowPlayingMovies(): Flow<PagingData<MovieResponseModel>?>

        suspend fun getGenreList(): Result<List<GenreResponseModel>>

        suspend fun getSearchResults(genreId: Int): Result<List<MovieResponseModel>>

    }

    interface LocalDataSource {

        suspend fun saveMovie(movieEntity: MovieEntity)

        suspend fun getMovie(id: Int): Result<MovieEntity>

    }

}