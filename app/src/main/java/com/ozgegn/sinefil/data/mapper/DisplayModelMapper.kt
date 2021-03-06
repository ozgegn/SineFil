package com.ozgegn.sinefil.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.ProviderModel
import com.ozgegn.sinefil.data.local.entity.GenreEntity
import com.ozgegn.sinefil.data.local.entity.MovieEntity
import com.ozgegn.sinefil.data.remote.MovieResponseModel
import com.ozgegn.sinefil.data.remote.response.GenreResponseModel
import com.ozgegn.sinefil.data.remote.response.ProvidersResponseModel

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

fun List<GenreEntity>.entityToGenreDisplayModelList() = this.map {
    it.toGenreDisplayModel()
}


fun List<MovieEntity>.entityToMovieDisplayList() = this.map {
    it.toMovieDisplayModel()
}

fun List<ProvidersResponseModel>.providerResponseToDisplayModel() = this.map {
    ProviderModel(
        it.logo_path,
        it.provider_name,
        it.provider_id
    )
}