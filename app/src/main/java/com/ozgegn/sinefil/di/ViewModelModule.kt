package com.ozgegn.sinefil.di

import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.features.movies.MoviesHomeViewModel
import com.ozgegn.sinefil.features.search.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMoviesHomeViewModel(moviesRepository: MoviesRepository): MoviesHomeViewModel =
        MoviesHomeViewModel(moviesRepository)

    @Provides
    @ViewModelScoped
    fun provideSearchMovieViewModel(moviesRepository: MoviesRepository): SearchViewModel =
        SearchViewModel(moviesRepository)
}