package com.ozgegn.sinefil.data.remote.response

data class GetGenreListResponse(
    val genres: List<GenreResponseModel>
)

data class GenreResponseModel(
    val id: Int,
    val name: String
)