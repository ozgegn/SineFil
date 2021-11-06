package com.ozgegn.sinefil.data.remote.response

import com.ozgegn.sinefil.data.remote.MovieResponseModel

data class FilterWithGenreResponse(
    val results: List<MovieResponseModel>
)