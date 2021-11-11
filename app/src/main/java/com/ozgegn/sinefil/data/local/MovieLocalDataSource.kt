package com.ozgegn.sinefil.data.local

import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.Result
import com.ozgegn.sinefil.data.local.entity.GenreEntity
import com.ozgegn.sinefil.data.local.entity.MovieEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesDataSource.LocalDataSource {

    override suspend fun saveMovie(movieEntity: MovieEntity) = withContext(ioDispatcher) {
        movieDao.add(movieEntity)
    }

    override suspend fun getMovie(id: Int): Result<MovieEntity> = withContext(ioDispatcher) {
        try {
            val movieResult = movieDao.get(id)
            Result.Success(movieResult)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveGenre(genreEntity: GenreEntity) = withContext(ioDispatcher) {
        genreDao.add(genreEntity)
    }

    override suspend fun getGenres(): Result<List<GenreEntity>> = withContext(ioDispatcher) {
        try {
            val genres = genreDao.getAll()
            Result.Success(genres)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}