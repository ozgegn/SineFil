package com.ozgegn.sinefil.di

import androidx.paging.PagingSource
import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.MoviesPagingSource
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.MoviesRemoteDataSource
import com.ozgegn.sinefil.data.remote.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRemoteDataSource(api: TmdbApi): MoviesDataSource.RemoteDataSource =
        MoviesRemoteDataSource(api)

}