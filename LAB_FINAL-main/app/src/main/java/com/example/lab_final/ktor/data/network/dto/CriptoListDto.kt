package com.example.lab_final.ktor.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CriptoListDto(
    val data: List<CriptoDto>
)