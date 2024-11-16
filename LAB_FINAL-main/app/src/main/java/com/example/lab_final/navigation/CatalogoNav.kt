package com.example.lab_final.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab_final.coins.Cripto
import com.example.lab_final.presentation.CatalogoMainScreen

@Composable
fun CatalogNav(navController: NavHostController, items: List<Cripto>) {
    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            CatalogoMainScreen(items = items, onItemClick = { item ->
                navController.navigate("detalle/${item.id}")
            })
        }
    }
}

//Esta aca por redundar, pero si lo quito el programa deja de funcionar, tonces aqui se queda