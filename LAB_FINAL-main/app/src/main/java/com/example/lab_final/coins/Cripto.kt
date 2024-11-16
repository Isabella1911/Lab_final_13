package com.example.lab_final.coins

import kotlinx.serialization.Serializable

@Serializable
data class Cripto(
    val id: String,
    val name: String,
    val priceUsd: Double,
    val changePercent24Hr: String,

    val symbol: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val lastUpdated: Long?
)