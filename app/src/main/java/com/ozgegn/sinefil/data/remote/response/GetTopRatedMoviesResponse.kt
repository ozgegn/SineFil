package com.ozgegn.sinefil.data.remote.response

import com.ozgegn.sinefil.data.remote.MovieResponseModel

data class GetTopRatedMoviesResponse(
    val results: List<MovieResponseModel>
)