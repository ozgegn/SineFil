package com.ozgegn.sinefil.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ozgegn.sinefil.BuildConfig
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.remote.TmdbApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideOkHttpClientProvider(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().also {
                if (BuildConfig.DEBUG) it.level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor =
        Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", context.resources.getString(R.string.tmdb_api_key))
                .addQueryParameter("language", "en-US")
                .build()

            val newRequest = chain.request().newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @ApplicationContext context: Context
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.tmdb_api_url))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

    @Provides
    fun provideApiService(retrofit: Retrofit): TmdbApi = retrofit.create(TmdbApi::class.java)

}