package com.edmalyon.sugarblood.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.edmalyon.sugarblood.R
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel

@Composable
fun BottomNavBar(
    navController: NavHostController,
    usuarioId: Int
) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        // Obtén el ViewModel
        val usuarioViewModel: UsuarioViewModel = viewModel()
        // Glucosa
        BottomNavigationItem(
            icon = {
                CustomIcon(
                    iconResId = R.drawable.glucosa,
                    contentDescription = "Profile"
                )
            },
            selected = false,
            onClick = {
                navController.navigate("listaGlucosa/${usuarioId}") {
                    // Puedes usar 'popUpTo' para limpiar el backstack si es necesario
                    launchSingleTop = true
                }
            }
        )

        // Ítem de registros
//        BottomNavigationItem(
//            icon = {
//                CustomIcon(
//                    iconResId = R.drawable.grafica,
//                    contentDescription = "Profile"
//                )
//            },
//            //label = { Text("registros") },
//            selected = false, // Controlar si este ítem está seleccionado
//            onClick = {
//                // Navegar a la pantalla de perfil
//                navController.navigate("listaGlucosa/${usuarioId}") {
//                    launchSingleTop = true
//                }
//            }
//        )

        // Ítem de alarmas
//        BottomNavigationItem(
//            icon = {
//                CustomIcon(
//                    iconResId = R.drawable.alarma,
//                    contentDescription = "Profile"
//                )
//            },
//            //label = { Text("registros") },
//            selected = false, // Controlar si este ítem está seleccionado
//            onClick = {
//                // Navegar a la pantalla de perfil
//                navController.navigate("listaGlucosa/${usuarioId}") {
//                    launchSingleTop = true
//                }
//            }
//        )

        // Ítem de Configuración
        BottomNavigationItem(
            icon = {
                CustomIcon(
                    iconResId = R.drawable.perfil,
                    contentDescription = "Profile"
                )
            },
            //label = { Text("Settings") },
            selected = false, // Controlar si este ítem está seleccionado
            onClick = {
                // Navegar a la pantalla de configuración
//                navController.navigate("listaGlucosa/${usuarioId}") {
//                    launchSingleTop = true
//                }
                usuarioViewModel.logout()
                navController.navigate("login") {

                    // Esto asegura que el login será la única pantalla en el backstack
                    popUpTo("login") { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }
}

@Composable
fun CustomIcon(
    @DrawableRes iconResId: Int, // Identificador del recurso drawable
    contentDescription: String,
    modifier: Modifier = Modifier.size(30.dp) // Puedes pasar otros modificadores si es necesario
) {
    Image(
        painter = painterResource(iconResId),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

