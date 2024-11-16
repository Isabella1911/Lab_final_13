package com.example.lab_final.ktor.domain.network


import com.example.lab_final.ktor.data.network.dto.CriptoDto
import com.example.lab_final.ktor.data.network.dto.CriptoListDto
import com.example.lab_final.ktor.domain.network.util.Result
import com.example.lab_final.ktor.domain.network.util.NetworkError

interface CriptoAPI {
    suspend fun getAllCriptos(): Result<CriptoListDto, NetworkError>
    suspend fun getCripto(id: String): Result<CriptoDto, NetworkError> // Cambiado a String
}
