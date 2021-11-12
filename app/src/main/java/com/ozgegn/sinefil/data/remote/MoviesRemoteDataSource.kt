package com.ozgegn.sinefil.data.remote

import androidx.paging.*
import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.MoviesPagingSource
import com.ozgegn.sinefil.data.NETWORK_PAGE_SIZE
import com.ozgegn.sinefil.data.Result
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel
import com.ozgegn.sinefil.data.remote.response.ProvidersResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val api: TmdbApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesDataSource.RemoteDataSource {

    override fun getNowPlayingMovies(): Flow<PagingData<MovieResponseModel>?> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    api
                )
            }
        ).flow
            .cachedIn(CoroutineScope(ioDispatcher))
    }

    override suspend fun getGenreList(): Result<List<GenreResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.getGenreList()
                if (result.isSuccessful) {
                    Result.Success(result.body()?.genres ?: listOf())
                } else {
                    Result.Error(Exception("Genres not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getSearchResults(genreId: Int): Result<List<MovieResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.filterWithGenre(genreId)
                if (result.isSuccessful) {
                    Result.Success(result?.body()?.results ?: listOf())
                } else {
                    Result.Error(Exception("Movies not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getProviders(region: String): Result<List<ProvidersResponseModel>> =
        withContext(ioDispatcher) {
            try {
                val result = api.getProviders(region)
                if (result.isSuccessful) {
                    Result.Success(result.body()?.results ?: listOf())
                }else {
                    Result.Error(Exception("Providers not found"))
                }
            }catch (e: Exception) {
                Result.Error(e)
            }
        }
}