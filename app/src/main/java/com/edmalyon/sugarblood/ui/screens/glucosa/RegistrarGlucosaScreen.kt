package com.edmalyon.sugarblood.ui.screens.glucosa


import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edmalyon.sugarblood.R
import com.edmalyon.sugarblood.components.BotonPrincipal
import com.edmalyon.sugarblood.components.CustomTopAppBar
import com.edmalyon.sugarblood.components.MainIconButton
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.AzulOscuro
import com.edmalyon.sugarblood.ui.theme.Color1
import com.edmalyon.sugarblood.ui.theme.Color2
import com.edmalyon.sugarblood.ui.theme.ColorButton
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGlucosaScreen(
    navController: NavHostController, glucosaViewModel: GlucosaViewModel, usuarioId: Int
) {
    val context = LocalContext.current
    var valorGlucosa by remember { mutableStateOf("") }
    var momentoGlucosa by remember { mutableStateOf("") }
    var pesoGlucosa by remember { mutableStateOf("") }
    var notaGlucosa by remember { mutableStateOf("") }

    var fechaHora by remember { mutableStateOf<Long?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    //var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember {
        mutableStateOf("Momento de medición")
    }
    var isDialogOpen by remember { mutableStateOf(false) }
    var useMmol by remember { mutableStateOf(false) }


    // Inicializar la variable fechaHora en el momento de la selección de fecha y hora
    if (showDatePickerDialog) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Establecer la fecha seleccionada en el calendario
                calendar.set(year, month, dayOfMonth)

                // Si no hemos seleccionado una hora aún, usamos la hora actual
                if (fechaHora == null) {
                    calendar.set(
                        Calendar.HOUR_OF_DAY,
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    )
                    calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE))
                } else {
                    // Si ya existe una fechaHora, mantenemos la hora anterior
                    calendar.timeInMillis = fechaHora!!
                }

                // Guardar solo la fecha (con la hora restablecida)
                fechaHora = calendar.timeInMillis

                // Cerrar el DatePickerDialog y abrir el TimePickerDialog
                showDatePickerDialog = false
                showTimePickerDialog = true
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

//    if (showTimePickerDialog) {
//        TimePickerDialog(
//            context,
//            { _, hourOfDay, minute ->
//                // Establecer la hora y minuto seleccionados en el calendario
//                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                calendar.set(Calendar.MINUTE, minute)
//
//                // Actualizar fechaHora con la fecha seleccionada + hora seleccionada
//                fechaHora = calendar.timeInMillis
//
//                // Cerrar el TimePickerDialog
//                showTimePickerDialog = false
//            },
//            calendar.get(Calendar.HOUR_OF_DAY),
//            calendar.get(Calendar.MINUTE),
//            true // Formato de 24 horas
//        ).show()
//    }


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { TitleBar(name = "Registrar") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = ColorButton
            ),
            navigationIcon = {
                MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                    navController.popBackStack()
                }
            })
        CustomTopAppBar(
            title = "Registrar", backBrush = Brush.linearGradient(
                colors = listOf(
                    Color1, Color2
                )
            ), icon = Icons.AutoMirrored.Filled.ArrowBack, onIconClick = {
                navController.popBackStack()
            }, color = Color.White
        )
    }, content = { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registrar Nivel de Glucosa", style = MaterialTheme.typography.titleLarge)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                OutlinedTextField(
                    value = valorGlucosa,
                    onValueChange = {
                        if (it.length <= 3 && it.all { char -> char.isDigit() }) {
                            valorGlucosa = it
                        }
                    },
                    label = { Text("Glucosa \n (${if (useMmol) "mmol/L" else "mg/dL"})") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(100.dp) // Ajusta el ancho según tus necesidades
                )
                Spacer(modifier = Modifier.width(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(checked = useMmol, onCheckedChange = { useMmol = it })
                    Text(text = "¿Tu glucometro\nmide en mmol/L?")
                }
            }

            // Selector de fecha y hora para tiempo_glucosa
            Row(
                modifier = Modifier
                    .clickable { showDatePickerDialog = true }
                    .padding(bottom = 16.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(width = 280.dp, height = 56.dp)
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (fechaHora != null)
                        SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).format(Date(fechaHora!!))
                    else
                        "Fecha y Hora",
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Abrir",
                    tint = MaterialTheme.colorScheme.primary
                )
            }


            var bottomSheetVisible by remember { mutableStateOf(false) }

            Row(modifier = Modifier
                .clickable { bottomSheetVisible = true }
                .padding(bottom = 16.dp)
                .border(
                    width = 1.dp, // Grosor del borde
                    color = MaterialTheme.colorScheme.primary, // Color del borde
                    shape = RoundedCornerShape(4.dp) // Bordes redondeados
                )
                .size(width = 280.dp, height = 56.dp) // Tamaño fijo: anchura y altura
                .padding(horizontal = 12.dp), // Espaciado interno después del borde
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    selectedOption, style = MaterialTheme.typography.bodyLarge
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
                            "Momento de medición", style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        listOf(
                            "Una hora antes de almorzar",
                            "Una hora después de almorzar",
                            "Una hora antes de comer",
                            "Una hora después de comer",
                            "Una hora antes de cenar",
                            "Una hora después de cenar"
                        ).forEach { option ->
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = pesoGlucosa,
                    onValueChange = {
                        if (it.length <= 5 && it.all { char -> char.isDigit() || char == '.' }) {
                            pesoGlucosa = it
                        }
                    },
                    label = { Text("Peso") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = notaGlucosa,
                    onValueChange = { notaGlucosa = it },
                    label = { Text("Nota") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                )
            }

            BotonPrincipal(
                name = "Registrar", backBrush = Brush.linearGradient(
                    colors = listOf(
                        ColorButton, AzulOscuro
                    )
                ), color = Color.White
            ) {
                if (momentoGlucosa == "Momento de medición") {
                    momentoGlucosa = ""
                }

                if (valorGlucosa.isNotBlank()) {
                    momentoGlucosa = selectedOption
                    val valorGlucosaFinal =
                        if (useMmol)
                            valorGlucosa.toFloat() * 18f
                        else
                            valorGlucosa.toFloat()

                    val tiempoMedicion = fechaHora ?: System.currentTimeMillis()
                    val nuevaGlucosa = Glucosa(
                        tiempo_glucosa = tiempoMedicion,
                        momento_glucosa = momentoGlucosa,
                        valor_glucosa = valorGlucosaFinal,
                        peso_glucosa = pesoGlucosa.toFloat(),
                        nota_glucosa = notaGlucosa,
                        id_usuario = usuarioId
                    )
                    glucosaViewModel.insertarGlucosa(nuevaGlucosa)
                    Toast.makeText(context, "Registrado exitosamente", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "Llene el campo de la glucosa", Toast.LENGTH_SHORT)
                        .show()
                }

                fechaHora = null
                valorGlucosa = ""
                momentoGlucosa = "Momento de medición"
                pesoGlucosa = ""
                notaGlucosa = ""
            }

        }

        if (isDialogOpen) {
            AlertDialog(onDismissRequest = { isDialogOpen = false },
                title = { Text("Registro Completo") },
                text = { Text("Usuario registrado exitosamente.") },
                confirmButton = {
                    Button(onClick = {
                        isDialogOpen = false
                    }) { Text("OK") }
                })
        }


    })
}



