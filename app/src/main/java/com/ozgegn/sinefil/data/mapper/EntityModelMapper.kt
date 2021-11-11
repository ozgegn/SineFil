package com.ozgegn.sinefil.data.mapper

import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.local.entity.GenreEntity
import com.ozgegn.sinefil.data.local.entity.MovieEntity
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel

fun MovieModel.toMovieEntityModel() = MovieEntity(
    backdrop_path,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    vote_average,
    vote_count
)

fun MovieEntity.toMovieDisplayModel() = MovieModel(
    backdrop_path,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    vote_average,
    vote_count
)

fun GenreModel.toGenreEntityModel() = GenreEntity(
    id, name
)

fun GenreEntity.toGenreDisplayModel() = GenreModel(
    id, name
)

fun GenreResponseModel.responseToEntityModel() = GenreEntity(
    id, name
)