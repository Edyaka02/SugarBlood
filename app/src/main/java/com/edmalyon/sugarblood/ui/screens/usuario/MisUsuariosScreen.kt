//package com.edmalyon.sugarblood.ui.screens
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.edmalyon.sugarblood.data.local.database.entities.Usuario
//import com.edmalyon.sugarblood.viewModels.UsuarioViewModel
//
//@Composable
//fun MisUsuariosScreen(
//    viewModel: UsuarioViewModel = hiltViewModel()
//) {
//    val usuarios = viewModel.usuarios.observeAsState(emptyList())
//
//
//    Scaffold (
//        topBar = {
//            Text(
//                text = "Mis Usuarios",
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
//    ){ padding ->
//        LazyColumn(
//            contentPadding = padding,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//
//            items(usuarios.value) { usuario ->
//                UsuarioItem(usuario)
//                Text(text = "hola")
//            }
//        }
//
//    }
//
//}
//
//@Composable
//fun UsuarioItem(usuario: Usuario) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(text = "Nombre:",
//            style = MaterialTheme.typography.titleMedium
//        )
//        Text(text = "Correo: ${usuario.email_usuario}",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}