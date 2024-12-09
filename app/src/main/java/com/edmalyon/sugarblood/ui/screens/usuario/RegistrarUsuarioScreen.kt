package com.edmalyon.sugarblood.ui.screens.usuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel

@Composable
fun RegistrarUsuarioScreen(
    usuarioViewModel: UsuarioViewModel = viewModel()
   // onUsuarioRegistrado: () -> Unit // Callback para navegar o realizar una acción después del registro
) {
    // Estados locales para los campos de entrada
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mensajeExito by remember { mutableStateOf(false) }
    var isDialogOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto para el nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para el correo
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para registrar
        Button(
            onClick = {
                if (nombre.isNotBlank() && email.isNotBlank()) {
                    val nuevoUsuario = Usuario(
                        id_usuario = 0, // Room asignará el ID automáticamente si está configurado como autoincremental
                        nombre_usuario = nombre,
                        email_usuario = email
                    )
                    usuarioViewModel.registrarUsuario(nuevoUsuario)
                    mensajeExito = true
                    isDialogOpen = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de éxito
        if (mensajeExito) {
            Text(
                text = "Usuario registrado con éxito",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
//            // Opcional: Ejecutar callback después del registro
//            LaunchedEffect(Unit) {
//                onUsuarioRegistrado()
//            }
        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { isDialogOpen = false },
                title = { Text("Registro Completo") },
                text = { Text("Usuario registrado exitosamente.") },
                confirmButton = {
                    Button(onClick = {
                        isDialogOpen = false
                    }) { Text("OK") }
                })
        }
    }
}