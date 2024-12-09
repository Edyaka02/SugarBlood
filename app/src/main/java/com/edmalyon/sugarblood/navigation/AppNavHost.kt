package com.edmalyon.sugarblood.navigation

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
//
//
//@Composable
//fun AppNavHost() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "registro_usuario"
//    ) {
//
//        composable("registro_usuario") {
//            RegistroUsuarioScreen(
//                onUserRegistered = {
//                    navController.navigate("usuario/{id_usuario}"){
//                        popUpTo("registro_usuario") { inclusive = true }
//                    }
//
//                }
//            )
//        }
//
//        composable("usuarios") {
//            MisUsuariosScreen()
//        }
//
////        composable("usuario/{id_usuario}") { backStackEntry ->
////            val id_usuario = backStackEntry.arguments?.getString("id_usuario")?.toInt() ?: 0
////            UsuarioScreen(
////                id_usuario = id_usuario
////            )
////        }
//    }
//}
//@Composable
//fun AppNavHost(
//    navController: NavHostController
//) {
//    NavHost(
//        navController = navController,
//        startDestination = "home"
//    ) {
//
//        composable("home") {
//            HomeScreen(
//
//            )
//        }
//
//        composable("registro_usuario") {
//            RegistroUsuarioScreen(
//                onUserRegistered = {
//                    navController.navigate("usuario/{id_usuario}"){
//                        popUpTo("registro_usuario") { inclusive = true }
//                    }
//
//                }
//            )
//        }
//
//        composable("usuario/{id_usuario}") { backStackEntry ->
//            val id_usuario = backStackEntry.arguments?.getString("id_usuario")?.toInt() ?: 0
//            UsuarioScreen(
//                id_usuario = id_usuario
//            )
//        }
//    }
//}

//fun AppNavHost(
//    navController: NavHostController,
//    startDestination: String,
//    onNavEvent: (NavEvent) -> Unit
//) {
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ) {
//        composable(Screen.Home.route) {
//            HomeScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Login.route) {
//            LoginScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Register.route) {
//            RegisterScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Profile.route) {
//            ProfileScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Reminders.route) {
//            RemindersScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Glucose.route) {
//            GlucoseScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//        composable(Screen.Information.route) {
//            InformationScreen(
//                onNavEvent = onNavEvent
//            )
//        }
//    }
//}