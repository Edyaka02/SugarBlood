package com.edmalyon.sugarblood.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edmalyon.sugarblood.components.BottomNavBar
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel
import com.edmalyon.sugarblood.ui.screens.HomeScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.ListaGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.RegistrarGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.usuario.LoginUsuarioScreen
import com.edmalyon.sugarblood.ui.screens.usuario.RegistrarUsuarioScreen

//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.edmalyon.sugarblood.ui.screens.HomeScreen
//import com.edmalyon.sugarblood.ui.screens.MisUsuariosScreen
//import com.edmalyon.sugarblood.ui.screens.RegistroUsuarioScreen
//import com.edmalyon.sugarblood.ui.screens.UsuarioScreen


@Composable
fun AppHost(navController: NavHostController) {
    val usuarioViewModel = hiltViewModel<UsuarioViewModel>()
    val glucosaViewModel = hiltViewModel<GlucosaViewModel>()

    val isAuthenticated by usuarioViewModel.isAuthenticated.collectAsState(initial = false)
    val usuarioId by usuarioViewModel.usuarioId.observeAsState(-1)

    Scaffold(
        bottomBar = {
            //BottomNavBar(navController = navController)
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
                //val usuarioViewModel = hiltViewModel<UsuarioViewModel>()
                LoginUsuarioScreen(usuarioViewModel, navController) // Pasar navController
            }
            composable("registrarUsuario") {
                //val usuarioViewModel = hiltViewModel<UsuarioViewModel>()
                RegistrarUsuarioScreen(usuarioViewModel, navController) // Pasar navController
            }

            //--------------------------------------------------------------------------------------
            // Glucosa
            //--------------------------------------------------------------------------------------

            composable(
                route = "listaGlucosa/{id_usuario}", // Definir la ruta con el argumento
                arguments = listOf(navArgument("id_usuario") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_usuario = backStackEntry.arguments?.getInt("id_usuario") ?: 0 // Obtener el argumento
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
                val id_usuario = backStackEntry.arguments?.getInt("id_usuario") ?: 0 // Obtener el argumento
                RegistrarGlucosaScreen(
                    navController = navController,
                    glucosaViewModel = glucosaViewModel,
                    usuarioId = id_usuario
                )
            }

            composable(
                route = "home/{id_usuario}",
                arguments = listOf(navArgument("id_usuario") { type = NavType.IntType })
            ) { backStackEntry ->
                val id_usuario = backStackEntry.arguments?.getInt("id_usuario") ?: 0
                HomeScreen (navController = navController, usuarioId = id_usuario)
            }
        }
    }
}