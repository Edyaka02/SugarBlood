package com.edmalyon.sugarblood.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.edmalyon.sugarblood.components.BottomNavBar
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel
import com.edmalyon.sugarblood.ui.screens.HomeScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.EditarGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.ListaGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.RegistrarGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.usuario.LoginUsuarioScreen
import com.edmalyon.sugarblood.ui.screens.usuario.RegistrarUsuarioScreen

@Composable
fun AppHost(navController: NavHostController) {
    val usuarioViewModel = hiltViewModel<UsuarioViewModel>()
    val glucosaViewModel = hiltViewModel<GlucosaViewModel>()

    val isAuthenticated by usuarioViewModel.isAuthenticated.collectAsState(initial = false)
    val usuarioId by usuarioViewModel.usuarioId.observeAsState(-1)

    Scaffold(
        bottomBar = {
            if (isAuthenticated && usuarioId != -1) {
                BottomNavBar(navController = navController, usuarioId = usuarioId)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {

            composable("login") {
                LoginUsuarioScreen(usuarioViewModel, navController)
            }
            composable("registrarUsuario") {
                RegistrarUsuarioScreen(usuarioViewModel, navController)
            }

            //--------------------------------------------------------------------------------------
            // Glucosa
            //--------------------------------------------------------------------------------------

            composable(
                route = "listaGlucosa/{id_usuario}", // Definir la ruta con el argumento
                arguments = listOf(navArgument("id_usuario") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_usuario =
                    backStackEntry.arguments?.getInt("id_usuario") ?: 0 // Obtener el argumento
                ListaGlucosaScreen(
                    navController = navController,
                    glucosaViewModel = hiltViewModel(),
                    usuarioId = id_usuario
                )
            }

            composable(
                route = "registrarGlucosa/{id_usuario}", // Definir la ruta con el argumento
                arguments = listOf(navArgument("id_usuario") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_usuario =
                    backStackEntry.arguments?.getInt("id_usuario") ?: 0 // Obtener el argumento
                RegistrarGlucosaScreen(
                    navController = navController,
                    glucosaViewModel = glucosaViewModel,
                    usuarioId = id_usuario
                )
            }

//            composable(
//                route = "editarGlucosa/{id_glucosa}/{id_usuario}",
//                arguments = listOf(navArgument("id_glucosa") { type = NavType.IntType },
//                    navArgument("id_usuario") { type = NavType.IntType })
//            ) { backStackEntry ->
//                val id_glucosa = backStackEntry.arguments?.getInt("id_glucosa") ?: 0
//                val id_usuario = backStackEntry.arguments?.getInt("id_usuario")
//                    ?: 0
//                RegistrarGlucosaScreen (
//                    navController = navController,
//                    glucosaViewModel = glucosaViewModel,
//                    usuarioId = id_usuario,
//                    glucosaId = id_glucosa
//                )
//            }
            composable(
                route = "editarGlucosa/{id_glucosa}",
                arguments = listOf(navArgument("id_glucosa") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_glucosa = backStackEntry.arguments?.getInt("id_glucosa") ?: 0
                EditarGlucosaScreen (
                    navController = navController,
                    glucosaViewModel = glucosaViewModel,
                    usuarioId = usuarioId,
                    glucosaId = id_glucosa
                )
            }

            composable(
                route = "home/{id_usuario}",
                arguments = listOf(navArgument("id_usuario") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_usuario = backStackEntry.arguments?.getInt("id_usuario") ?: 0
                HomeScreen(navController = navController, usuarioId = id_usuario)
            }
        }
    }
}