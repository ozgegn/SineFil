package com.ozgegn.sinefil.data.remote

import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val api: TmdbApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesDataSource.MoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): Result<List<MovieResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.getPopularMovies(page)
                if (result.isSuccessful)
                    Result.Success(result.body()?.results ?: listOf())
                else
                    Result.Error(Exception("Movies not found"))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getNowPlayingMovies(page: Int): Result<List<MovieResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.getNowPlayingMovies(page)
                if (result.isSuccessful)
                    Result.Success(result.body()?.results ?: listOf())
                else
                    Result.Error(Exception("Movies not found"))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getTopRatedMovies(page: Int): Result<List<MovieResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.getTopRatedMovies(page)
                if (result.isSuccessful)
                    Result.Success(result.body()?.results ?: listOf())
                else
                    Result.Error(Exception("Movies not found"))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}