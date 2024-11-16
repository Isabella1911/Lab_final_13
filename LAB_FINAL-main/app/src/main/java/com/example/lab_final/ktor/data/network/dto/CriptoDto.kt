package com.example.lab_final.ktor.data.network.dto

import com.example.lab_final.coins.Cripto
import kotlinx.serialization.Serializable

@Serializable
data class CriptoDto(
    val id: String,
    val name: String,
    val priceUsd: String?, // Cambiado a String
    val changePercent24Hr: String?, // Cambiado a String
    val symbol: String?,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?
)

fun CriptoDto.mapToCriptoModel(): Cripto {
    return Cripto(
        id = id,
        name = name,
        priceUsd = priceUsd?.toDoubleOrNull() ?: 0.0, // Convierte String a Double o usa 0.0 como valor por defecto
        changePercent24Hr = changePercent24Hr ?: "0.0", // Usa un valor por defecto si es nulo
        symbol = symbol ?: "N/A",
        supply = supply ?: "N/A",
        maxSupply = maxSupply ?: "N/A",
        marketCapUsd = marketCapUsd ?: "N/A",
        lastUpdated = System.currentTimeMillis()
    )
}
