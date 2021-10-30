package com.ozgegn.sinefil.repository

import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.Result
import com.ozgegn.sinefil.data.mapper.toDisplayModelList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesDataSource.MoviesRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Result<List<MovieModel>> {
        return try {
            when (val result = remoteDataSource.getPopularMovies(page)) {
                is Result.Success -> {
                    Result.Success(result.data.toDisplayModelList())
                }
                else -> {
                    Result.Success(listOf())
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Result<List<MovieModel>> {
        return try {
            when (val result = remoteDataSource.getNowPlayingMovies(page)) {
                is Result.Success -> {
                    Result.Success(result.data.toDisplayModelList())
                }
                else -> {
                    Result.Success(listOf())
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTopRatedMovies(page: Int): Result<List<MovieModel>> {
        return try {
            when (val result = remoteDataSource.getTopRatedMovies(page)) {
                is Result.Success -> {
                    Result.Success(result.data.toDisplayModelList())
                }
                else -> {
                    Result.Success(listOf())
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}