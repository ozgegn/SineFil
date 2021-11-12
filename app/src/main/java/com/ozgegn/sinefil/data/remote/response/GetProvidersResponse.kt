package com.ozgegn.sinefil.data.remote.response

data class GetProvidersResponse(
    val results: List<ProvidersResponseModel>
)

data class ProvidersResponseModel(
    val logo_path: String?,
    val provider_name: String,
    val provider_id: Int
)