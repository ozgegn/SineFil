package com.ozgegn.sinefil.data.remote

import com.ozgegn.sinefil.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<GetPopularMoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<GetNowPlayingResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<GetTopRatedMoviesResponse>

    @GET("genre/movie/list")
    suspend fun getGenreList(): Response<GetGenreListResponse>

    @GET("discover/movie")
    suspend fun filterWithGenre(
        @Query("with_genres") genreId: Int
    ): Response<FilterWithGenreResponse>

}