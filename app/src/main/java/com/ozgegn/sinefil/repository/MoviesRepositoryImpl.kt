package com.ozgegn.sinefil.repository

import androidx.paging.PagingData
import com.ozgegn.sinefil.data.*
import com.ozgegn.sinefil.data.mapper.*
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesDataSource.RemoteDataSource,
    private val localDataSource: MoviesDataSource.LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): Flow<PagingData<MovieResponseModel>?> {
        return remoteDataSource.getNowPlayingMovies()
    }

    override suspend fun getGenres(): Result<List<GenreModel>> {
        return try {
            when (val result = remoteDataSource.getGenreList()) {
                is Result.Success -> {
                    Result.Success(result.data.toGenreDisplayModelList())
                }
                else -> {
                    Result.Success(listOf())
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveMovie(movieModel: MovieModel) {
        localDataSource.saveMovie(movieModel.toMovieEntityModel())
    }

    override suspend fun getMovie(id: Int): Result<MovieModel> {
        return try {
            when (val result = localDataSource.getMovie(id)) {
                is Result.Success -> Result.Success(result.data.toMovieDisplayModel())
                else -> Result.Error(Exception("Movie not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getSearchResults(genreId: Int): Result<List<MovieModel>> {
        return try {
            when (val result = remoteDataSource.getSearchResults(genreId)) {
                is Result.Success -> Result.Success(result.data.toMovieDisplayModelList())
                else -> Result.Error(Exception("Movies not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getWatchList(): Result<List<MovieModel>> {
        return try {
            when (val result = localDataSource.getWatchList()) {
                is Result.Success -> Result.Success(result.data.entityToMovieDisplayList())
                else -> Result.Success(listOf())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}