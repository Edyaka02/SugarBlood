package com.edmalyon.sugarblood.ui.screens.usuario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import com.edmalyon.sugarblood.viewModels.UsuarioViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel


@Composable
fun UsuarioScreen(usuarioViewModel: UsuarioViewModel = viewModel()) {
    // Obtén el estado directamente desde el ViewModel
    val usuarioState = usuarioViewModel.usuario.value

    // Llamamos a la función para obtener el usuario cuando la UI se compone
    LaunchedEffect(Unit) {
        usuarioViewModel.obtenerUsuarioPorId(1) // Cambia el ID según sea necesario
    }

    // Si el usuario no es nulo, mostramos sus datos
    usuarioState?.let { usuario ->
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Nombre: ${usuario.nombre_usuario}")
            Text("Correo: ${usuario.email_usuario}")
            // Agrega más campos si lo necesitas
        }
    } ?: run {
        // Si no se ha obtenido el usuario aún, mostramos un cargando
        CircularProgressIndicator()
    }
}



//@Composable
//fun UsuarioScreen(
//    id_usuario: Int,
//    viewModel: UsuarioViewModel = hiltViewModel()
//) {
//    val usuario = viewModel.usuario.observeAsState()
//    val usuarioInformacion = viewModel.usuarioInformacion.observeAsState()
//
//    LaunchedEffect(id_usuario) {
//        viewModel.obtenerUsuario(id_usuario)
//        viewModel.obtenerInformacion(id_usuario)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        usuario.value?.let { usuario ->
//            Text(
//                text = "Nombre: ${usuario.nombre_usuario}",
//                style = MaterialTheme.typography.titleMedium,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Correo: ${usuario.email_usuario}",
//                style = MaterialTheme.typography.bodyLarge
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        usuarioInformacion.value?.let { usuarioInformacion ->
//            usuarioInformacion.informacion?.let { informacion ->
//                Text(
//                    text = "Información",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "Nombre: ${informacion.nombre_informacion}",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Text(
//                    text = "Apellido: ${informacion.apellido_informacion}",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Text(
//                    text = "Altura: ${informacion.altura_informacion}",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Text(
//                    text = "Edad: ${informacion.edad_informacion}",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//
//
//        }
//    }
//}

