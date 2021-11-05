package com.ozgegn.sinefil.di

import android.content.Context
import androidx.room.Room
import com.ozgegn.sinefil.data.MoviesDataSource
import com.ozgegn.sinefil.data.local.AppDatabase
import com.ozgegn.sinefil.data.local.MovieDao
import com.ozgegn.sinefil.data.local.MovieLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "sinefilDB")
        .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    fun provideLocalDataSource(dao: MovieDao): MoviesDataSource.LocalDataSource = MovieLocalDataSource(dao)

}