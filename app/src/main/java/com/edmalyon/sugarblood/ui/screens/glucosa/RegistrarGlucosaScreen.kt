package com.edmalyon.sugarblood.ui.screens.glucosa

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.AzulOscuro
import com.edmalyon.sugarblood.ui.theme.ColorButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGlucosaScreen(
    navController: NavHostController,
    glucosaViewModel: GlucosaViewModel, //= hiltViewModel(),
    usuarioId: Int
    //onGlucosaRegistrada: () -> Unit // Navegar después del registro
) {
//    var nivelGlucosa by remember { mutableStateOf("") }
//    var fecha by remember { mutableStateOf("") }


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

                Box(
                    ) {
                    Text(
                        text = selectedOption,
                        modifier = Modifier
                            .clickable { expanded = true }
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Opción 1") }, onClick = {
                                selectedOption = "Opción 1"
                                expanded = false
                            })
                        DropdownMenuItem(
                            text = { Text("Opción 2") }, onClick = {
                                selectedOption = "Opción 2"
                                expanded = false
                            })
                        DropdownMenuItem(
                            text = { Text("Opción 3") }, onClick = {
                                selectedOption = "Opción 3"
                                expanded = false
                            })
                        DropdownMenuItem(
                            text = { Text("Opción 4") }, onClick = {
                                selectedOption = "Opción 4"
                                expanded = false
                            })
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
//                if (valorGlucosa.isNotBlank() && momentoGlucosa.isNotBlank()
//                    && pesoGlucosa.isNotBlank()
//                ) {
                        val nuevaGlucosa = Glucosa(
                            tiempo_glucosa = System.currentTimeMillis(),
                            momento_glucosa = momentoGlucosa,
                            valor_glucosa = valorGlucosa.toFloat(),
                            peso_glucosa = pesoGlucosa.toFloat(),
                            nota_glucosa = notaGlucosa,
                            id_usuario = usuarioId
                        )
                        glucosaViewModel.insertarGlucosa(nuevaGlucosa)
                        isDialogOpen = true
                        //onGlucosaRegistrada() // Navega o confirma el registro
                        //}
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
                    })
            }
        }
    )
}

