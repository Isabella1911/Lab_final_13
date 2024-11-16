package com.example.lab_final.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_final.coins.Cripto
import com.example.lab_final.presentation.CatalogoMainScreen
import com.example.lab_final.presentation.CriptoDetailScreen

@Composable
fun AppNav(items: List<Cripto>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            CatalogoMainScreen(
                items = items,
                onItemClick = { item ->
                    navController.navigate("detalle/${item.id}")
                }
            )
        }
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val item = items.find { it.id == id }
            if (item != null) {
                CriptoDetailScreen(item = item, navController = navController)
            } else {
                // Opcional: Manejo de error si no se encuentra el id
            }
        }
    }
}



