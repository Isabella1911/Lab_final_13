package com.example.lab_final

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lab_final.coins.Cripto
import com.example.lab_final.navigation.AppNav
import com.example.lab_final.ui.theme.LAB_FINALTheme
import com.example.lab_final.ktor.domain.network.CriptoAPI
import com.example.lab_final.ktor.data.network.KtorCriptoAPI
import com.example.lab_final.ktor.domain.network.util.NetworkError
import com.example.lab_final.ktor.data.network.dto.mapToCriptoModel
import com.example.lab_final.ktor.domain.network.util.onError
import com.example.lab_final.ktor.domain.network.util.onSuccess
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LAB_FINALTheme {
                val cryptoApi: CriptoAPI = KtorCriptoAPI()
                var cryptoList by remember { mutableStateOf<List<Cripto>?>(null) }
                var isLoading by remember { mutableStateOf(true) }
                var errorMessage by remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        isLoading = true
                        val result = cryptoApi.getAllCriptos()
                        result
                            .onSuccess { dto ->
                                cryptoList = dto.data.map { it.mapToCriptoModel() }
                                errorMessage = null
                            }
                            .onError { error ->
                                errorMessage = when (error) {
                                    NetworkError.NO_INTERNET -> "No hay conexión a internet."
                                    NetworkError.SERIALIZATION -> "Error al procesar los datos."
                                    NetworkError.UNKNOWN -> "Error desconocido."
                                    else -> "Error de red."
                                }
                            }
                        isLoading = false
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    when {
                        isLoading -> {
                            Text("Cargando...")
                        }
                        errorMessage != null -> {
                            Text("Error: $errorMessage")
                        }
                        cryptoList != null -> {
                            AppNav(items = cryptoList!!)
                        }
                    }
                }
            }
        }
    }
}