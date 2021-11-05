package com.ozgegn.sinefil.data

import com.ozgegn.sinefil.data.local.entity.MovieEntity
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel

interface MoviesDataSource {

    interface RemoteDataSource {

        suspend fun getPopularMovies(page: Int): Result<List<MovieResponseModel>>

        suspend fun getNowPlayingMovies(page: Int): Result<List<MovieResponseModel>>

        suspend fun getTopRatedMovies(page: Int): Result<List<MovieResponseModel>>

        suspend fun getGenreList(): Result<List<GenreResponseModel>>

    }

    interface LocalDataSource {

        suspend fun saveMovie(movieEntity: MovieEntity)

        suspend fun getMovie(id: Int): Result<MovieEntity>

    }

}