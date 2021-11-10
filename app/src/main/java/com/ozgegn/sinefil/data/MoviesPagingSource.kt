package com.ozgegn.sinefil.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.TmdbApi
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val api: TmdbApi
) : PagingSource<Int, MovieResponseModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponseModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseModel> {
        val pageIndex = params.key ?: 1
        return try {
            val response = api.getNowPlayingMovies(pageIndex)
            val movies = response.body()?.results ?: listOf()
            val nextKey = if (movies.isNullOrEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            Timber.e(e)
            return LoadResult.Page(data = listOf(), null, null)
        } catch (e: HttpException) {
            Timber.e(e)
            return LoadResult.Page(data = listOf(), null, null)
        }
    }
}

const val NETWORK_PAGE_SIZE = 25