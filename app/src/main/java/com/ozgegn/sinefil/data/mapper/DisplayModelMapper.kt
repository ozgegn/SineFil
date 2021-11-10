package com.ozgegn.sinefil.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel

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

fun List<MovieResponseModel>.toMovieDisplayModelList(): List<MovieModel> = this.map {
    it.toDisplayModel()
}

fun PagingData<MovieResponseModel>.pagingToMovieDisplayModelList(): PagingData<MovieModel> =
    this.map {
        it.toDisplayModel()
    }

fun GenreResponseModel.toDisplayModel() = GenreModel(
    id, name
)

fun List<GenreResponseModel>.toGenreDisplayModelList() = this.map {
    it.toDisplayModel()
}