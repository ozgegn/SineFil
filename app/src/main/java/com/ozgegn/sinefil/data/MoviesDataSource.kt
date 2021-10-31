package com.ozgegn.sinefil.data

import com.ozgegn.sinefil.data.remote.MovieResponseModel

interface MoviesDataSource {

    interface RemoteDataSource {

        suspend fun getPopularMovies(page: Int): Result<List<MovieResponseModel>>

        suspend fun getNowPlayingMovies(page: Int): Result<List<MovieResponseModel>>

        suspend fun getTopRatedMovies(page: Int): Result<List<MovieResponseModel>>

    }

}