package com.ozgegn.sinefil.data.remote.response

import com.ozgegn.sinefil.data.remote.MovieResponseModel

data class GetNowPlayingResponse(
    val results: List<MovieResponseModel>
)