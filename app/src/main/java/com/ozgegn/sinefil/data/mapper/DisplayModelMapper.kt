package com.ozgegn.sinefil.data.mapper

import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.remote.MovieResponseModel

fun MovieResponseModel.toDisplayModel(): MovieModel = MovieModel(
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

fun List<MovieResponseModel>.toDisplayModelList(): List<MovieModel> = this.map {
    it.toDisplayModel()
}