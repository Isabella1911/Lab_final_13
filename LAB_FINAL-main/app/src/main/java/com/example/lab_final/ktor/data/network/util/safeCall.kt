package com.example.lab_final.ktor.data.network.util

import com.example.lab_final.ktor.domain.network.util.NetworkError
import com.example.lab_final.ktor.domain.network.util.Result
import io.ktor.client.call.body
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

/**
 * Realiza una llamada de red de manera segura, manejando excepciones comunes.
 *
 * Agregamos el generico T que define el tipo de dato esperado en la respuesta.
 * @param execute Una función suspendida que realiza la llamada de red y devuelve un HttpResponse.
 * @return Un Result que contiene el dato deserializado de tipo T en caso de éxito,
 *         o un NetworkError en caso de fallo.
 *
 * Esta función maneja los siguientes errores:
 * - NO_INTERNET: Cuando no se puede resolver la dirección del servidor.
 * - SERIALIZATION: Cuando hay un problema al deserializar la respuesta.
 * - UNKNOWN: Para cualquier otra excepción no manejada específicamente.
 *
 * Puedes copiar y pegar esta funcion
 */
import kotlinx.serialization.SerializationException
import io.ktor.client.statement.HttpResponse
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.ensureActive

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        println("Error de deserialización: ${e.localizedMessage}") // Log detallado aquí
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        println("Error desconocido: ${e.localizedMessage}") // Log para errores desconocidos
        return Result.Error(NetworkError.UNKNOWN)
    }

    return try {
        Result.Success(response.body())
    } catch (e: SerializationException) {
        println("Error de deserialización al procesar la respuesta: ${e.localizedMessage}") // Log aquí
        Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        println("Error desconocido al procesar la respuesta: ${e.localizedMessage}") // Log aquí
        Result.Error(NetworkError.UNKNOWN)
    }
}
