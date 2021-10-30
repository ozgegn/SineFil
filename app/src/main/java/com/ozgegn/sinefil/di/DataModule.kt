package com.ozgegn.sinefil.di

import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.remote.MoviesRemoteDataSource
import com.ozgegn.sinefil.data.remote.TmdbApi
import com.ozgegn.sinefil.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteDataSource(api: TmdbApi): MoviesDataSource.MoviesRemoteDataSource =
        MoviesRemoteDataSource(api)

    @Provides
    @ViewModelScoped
    fun provideMoviesRepository(remoteDataSource: MoviesRemoteDataSource): MoviesRepository =
        MoviesRepositoryImpl(remoteDataSource)

}