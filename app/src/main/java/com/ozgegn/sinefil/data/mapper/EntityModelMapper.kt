package com.ozgegn.sinefil.data.mapper

import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.local.entity.MovieEntity

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