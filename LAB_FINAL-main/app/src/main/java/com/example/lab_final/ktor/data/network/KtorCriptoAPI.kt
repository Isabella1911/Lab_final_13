package com.example.lab_final.ktor.data.network

import com.example.lab_final.ktor.data.network.dto.CriptoDto
import com.example.lab_final.ktor.data.network.dto.CriptoListDto
import com.example.lab_final.ktor.data.network.util.safeCall
import com.example.lab_final.ktor.domain.network.CriptoAPI
import com.example.lab_final.ktor.domain.network.util.Result
import com.example.lab_final.ktor.domain.network.util.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCriptoAPI : CriptoAPI {
    private val httpClient: HttpClient = HttpClientFactory.create()

    override suspend fun getAllCriptos(): Result<CriptoListDto, NetworkError> {
        return safeCall {
            httpClient.get("https://api.coincap.io/v2/assets")
        }
    }

    override suspend fun getCripto(id: String): Result<CriptoDto, NetworkError> {
        return safeCall {
            httpClient.get("https://api.coincap.io/v2/assets/$id")
        }
    }
}
