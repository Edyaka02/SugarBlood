package com.edmalyon.sugarblood.ui.screens.glucosa


import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.ColorButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGlucosaScreen(
    navController: NavHostController,
    glucosaViewModel: GlucosaViewModel,
    usuarioId: Int
) {
    val context = LocalContext.current
    var valorGlucosa by remember { mutableStateOf("") }
    var momentoGlucosa by remember { mutableStateOf("") }
    var pesoGlucosa by remember { mutableStateOf("") }
    var notaGlucosa by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember {
        mutableStateOf("Seleccione una opción")
    }
    var isDialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(name = "Registrar") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ColorButton
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Registrar Nivel de Glucosa", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = valorGlucosa,
                    onValueChange = { valorGlucosa = it },
                    label = { Text("Nivel de Glucosa") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                var bottomSheetVisible by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .clickable { bottomSheetVisible = true }
                        .padding(top = 8.dp)
                        .border(
                            width = 1.dp, // Grosor del borde
                            color = MaterialTheme.colorScheme.primary, // Color del borde
                            shape = RoundedCornerShape(4.dp) // Bordes redondeados
                        )
                        .size(width = 280.dp, height = 56.dp) // Tamaño fijo: anchura y altura
                        .padding(horizontal = 12.dp), // Espaciado interno después del borde
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        selectedOption,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir",
                        tint = MaterialTheme.colorScheme.primary // Color del ícono
                    )
                }


                if (bottomSheetVisible) {
                    ModalBottomSheet(onDismissRequest = { bottomSheetVisible = false }) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Seleccione un Momento",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            listOf("Mañana", "Tarde", "Noche").forEach { option ->
                                TextButton(onClick = {
                                    selectedOption = option
                                    bottomSheetVisible = false
                                }) {
                                    Text(option)
                                }
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = pesoGlucosa,
                    onValueChange = { pesoGlucosa = it },
                    label = { Text("Peso") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = notaGlucosa,
                    onValueChange = { notaGlucosa = it },
                    label = { Text("Nota") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                )

                Button(
                    onClick = {
                        if (momentoGlucosa.equals("Seleccione una opción")) {
                            momentoGlucosa = ""
                        }

                        if (valorGlucosa.isNotBlank()) {
                            momentoGlucosa = selectedOption
                            val nuevaGlucosa = Glucosa(
                                tiempo_glucosa = System.currentTimeMillis(),
                                momento_glucosa = momentoGlucosa,
                                valor_glucosa = valorGlucosa.toFloat(),
                                peso_glucosa = pesoGlucosa.toFloat(),
                                nota_glucosa = notaGlucosa,
                                id_usuario = usuarioId
                            )
                            glucosaViewModel.insertarGlucosa(nuevaGlucosa)
                            Toast.makeText(context, "Llene el campo de la glucosa", Toast.LENGTH_SHORT).show()
                            //isDialogOpen = true
                        } else {
                            Toast.makeText(context, "Llene el campo de la glucosa", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar")
                }
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
                    }
                )
            }


        }
    )
}

