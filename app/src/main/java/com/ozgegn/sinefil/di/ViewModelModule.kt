package com.ozgegn.sinefil.di

import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.features.movieDetail.MovieDetailViewModel
import com.ozgegn.sinefil.features.movies.MoviesHomeViewModel
import com.ozgegn.sinefil.features.search.SearchViewModel
import com.ozgegn.sinefil.features.service.StreamServicesViewModel
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

    @Provides
    @ViewModelScoped
    fun provideMovieDetailViewModel(moviesRepository: MoviesRepository): MovieDetailViewModel =
        MovieDetailViewModel(moviesRepository)

    @Provides
    @ViewModelScoped
    fun provideStreamServicesViewModel(moviesRepository: MoviesRepository): StreamServicesViewModel =
        StreamServicesViewModel(moviesRepository)
}