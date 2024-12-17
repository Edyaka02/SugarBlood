package com.edmalyon.sugarblood.ui.screens.usuario

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.edmalyon.sugarblood.components.BotonPrincipal
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import com.edmalyon.sugarblood.data.local.database.viewModels.UsuarioViewModel
import com.edmalyon.sugarblood.ui.theme.AzulOscuro
import com.edmalyon.sugarblood.ui.theme.ColorButton
import com.edmalyon.sugarblood.ui.theme.ColorPrimario
import com.edmalyon.sugarblood.ui.theme.ColorSecondario
import com.edmalyon.sugarblood.ui.theme.Imagenes

@Composable
fun LoginUsuarioScreen(
    usuarioViewModel: UsuarioViewModel,
    navController: NavController
    // onUsuarioRegistrado: () -> Unit // Callback para navegar o realizar una acción después del registro
) {
    // Estados locales para los campos de entrada
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Observando el resultado del inicio de sesión
    val loginResult by usuarioViewModel.loginResult.observeAsState()
    val usuarioId by usuarioViewModel.usuarioId.observeAsState()


    // Manejar los cambios en el resultado del inicio de sesión
    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            result.onSuccess { usuario ->
                //navController.navigate("home/${usuario.id_usuario}")
                navController.navigate("listaGlucosa/${usuario.id_usuario}")
            }.onFailure {
                Toast.makeText(
                    context,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors = listOf(ColorButton, AzulOscuro))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = Imagenes.Logo_letras,
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .size(150.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = ColorSecondario
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Iniciar sesión",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(15.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                BotonPrincipal(
                    name = "Iniciar sesión",
                    backBrush = Brush.linearGradient(
                        colors = listOf(ColorButton, AzulOscuro)
                    ),
                    color = Color.White
                ) {
                    if (username.isNotBlank() && password.isNotBlank()) {
                        usuarioViewModel.iniciarSesion(username, password)
                    } else {
                        Toast.makeText(context, "Llene los campos", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box {
                    Text(
                        text = "¿No tienes una cuenta?",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Box {
                    BotonPrincipal(
                        name = "Registrarse",
                        backBrush = Brush.linearGradient(
                            colors = listOf(Color.White, Color.White)
                        ),
                        color = ColorPrimario
                    ) {
                        navController.navigate("registrarUsuario")
                    }
                }
            }

        }
    }

}
