package com.edmalyon.sugarblood.ui.screens.usuario

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.VisualTransformation
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
fun RegistrarUsuarioScreen(
    usuarioViewModel: UsuarioViewModel, //= viewModel(),
    navController: NavController
    // onUsuarioRegistrado: () -> Unit // Callback para navegar o realizar una acción después del registro
) {
    val context = LocalContext.current
    // Estados locales para los campos de entrada
    var nombre by remember { mutableStateOf("") }
    //var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }
    var mensajeDialogo by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    //Observando el resultado de la inserción
    val insercionResultado by usuarioViewModel.insercionResultado.observeAsState()
    //Manejar los cambios en el resultado de inserción
    LaunchedEffect(insercionResultado) {
        insercionResultado?.let { resultado ->
            resultado.onSuccess {
                mensajeDialogo = "Usuario registrado exitosamente."
                isDialogOpen = true
                //navController.navigate("home/${usuarioId.}")
            }.onFailure {
                mensajeDialogo = "El nombre de usuario ya existe"
                isDialogOpen = true
            }
        }
    }

    LaunchedEffect(insercionResultado) {
        insercionResultado?.let { resultado ->
            resultado.onSuccess { usuarioId ->
                mensajeDialogo = "Usuario registrado exitosamente."
                isDialogOpen = true
                navController.navigate("home/${usuarioId}")
            }.onFailure {
                mensajeDialogo = "El nombre de usuario ya existe"
                isDialogOpen = true
            }
        }
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(colors = listOf(ColorButton, AzulOscuro))),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = Imagenes.Logo_letras,
                contentDescription = "Avatar",
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
                elevation = CardDefaults.cardElevation(8.dp),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Crea un perfil",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(15.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, start = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, start = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        //visualTransformation = PasswordVisualTransformation(),
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, start = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = confirmarPassword,
                        onValueChange = { confirmarPassword = it },
                        label = { Text("Confirmar Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BotonPrincipal(
                        name = "Registrar",
                        backBrush = Brush.linearGradient(
                            colors = listOf(
                                ColorButton,
                                AzulOscuro
                            )
                        ),
                        color = Color.White
                    ) {
                        if (nombre.isNotBlank() && password.isNotBlank() && confirmarPassword.isNotBlank()) {
                            val nuevoUsuario = Usuario(
                                id_usuario = 0,
                                nombre_usuario = nombre,
                                password_usuario = password,
                                password_confirmar_usuario = confirmarPassword
                            )
                            usuarioViewModel.registrarUsuario(nuevoUsuario)
                            isDialogOpen = true
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                        //.padding(end = 8.dp)
                    ) {
                        Text(
                            text = "¿Ya tienes una cuenta?",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Box(
                        modifier = Modifier
                        //.padding(start = 8.dp),
                    ) {
                        BotonPrincipal(
                            name = "Iniciar sesión",
                            backBrush = Brush.linearGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.White
                                )
                            ),
                            color = ColorPrimario
                        ) {
                            navController.navigate("login")
                        }
                    }
                }


            }

        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { isDialogOpen = false },
                title = { Text("Registro de Usuario") },
                text = { Text(mensajeDialogo) },
                containerColor = Color.White,
                confirmButton = {
                    BotonPrincipal(
                        name = "OK",
                        backBrush = Brush.linearGradient(
                            colors = listOf(
                                ColorButton,
                                AzulOscuro
                            )
                        ),
                        color = Color.White
                    ) {
                        isDialogOpen = false
                    }
                }
            )
        }
    }
