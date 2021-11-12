package com.ozgegn.sinefil.data

data class ProviderModel(
    val logoPath: String?,
    val providerName: String,
    val providerId: Int
) {
    fun getProviderLogo() = "https://image.tmdb.org/t/p/original/$logoPath"
}