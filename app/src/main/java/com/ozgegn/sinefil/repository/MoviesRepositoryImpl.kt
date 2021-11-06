package com.ozgegn.sinefil.repository

import com.ozgegn.sinefil.data.*
import com.ozgegn.sinefil.data.mapper.toGenreDisplayModelList
import com.ozgegn.sinefil.data.mapper.toMovieDisplayModel
import com.ozgegn.sinefil.data.mapper.toMovieDisplayModelList
import com.ozgegn.sinefil.data.mapper.toMovieEntityModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesDataSource.RemoteDataSource,
    private val localDataSource: MoviesDataSource.LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Result<List<MovieModel>> {
        return try {
            when (val result = remoteDataSource.getPopularMovies(page)) {
                is Result.Success -> {
                    Result.Success(result.data.toMovieDisplayModelList())
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
                    Result.Success(result.data.toMovieDisplayModelList())
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
                    Result.Success(result.data.toMovieDisplayModelList())
                }
                else -> {
                    Result.Success(listOf())
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
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
}