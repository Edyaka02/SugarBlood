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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.edmalyon.sugarblood.components.BotonPrincipal
import com.edmalyon.sugarblood.components.MainIconButton
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.AzulOscuro
import com.edmalyon.sugarblood.ui.theme.ColorButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarGlucosaScreen(
    navController: NavHostController,
    glucosaViewModel: GlucosaViewModel,
    usuarioId: Int,
    glucosaId: Int? = null
) {
    val context = LocalContext.current

    val existingGlucosa by rememberUpdatedState(newValue = glucosaId?.let {
        glucosaViewModel.obtenerGlucosaPorId(
            it
        ).observeAsState().value
    })

    var valorGlucosa by remember {
        mutableStateOf(
            existingGlucosa?.valor_glucosa?.toString() ?: ""
        )
    }

    var momentoGlucosa by remember { mutableStateOf(existingGlucosa?.momento_glucosa ?: "") }
    var pesoGlucosa by remember { mutableStateOf(existingGlucosa?.peso_glucosa?.toString() ?: "") }
    var notaGlucosa by remember { mutableStateOf(existingGlucosa?.nota_glucosa ?: "") }

    var fechaHora by remember { mutableStateOf(0L) }
    LaunchedEffect(existingGlucosa) { existingGlucosa?.let { fechaHora = it.tiempo_glucosa } }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }
    //val calendar = Calendar.getInstance()



    var selectedOption by remember {
        mutableStateOf(
            existingGlucosa?.momento_glucosa ?: "Momento de medición"
        )
    }
    var isDialogOpen by remember { mutableStateOf(false) }
    var useMmol by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    if (fechaHora != 0L) {
        calendar.timeInMillis = fechaHora
    }

    LaunchedEffect(existingGlucosa) {
        existingGlucosa?.let {
            valorGlucosa =
                it.valor_glucosa.toString()
            momentoGlucosa = it.momento_glucosa.toString()
            pesoGlucosa = it.peso_glucosa.toString()
            notaGlucosa = it.nota_glucosa.toString()
            selectedOption = it.momento_glucosa.toString()
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
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
//                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                calendar.set(Calendar.MINUTE, minute)
//                fechaHora = calendar.timeInMillis
//                showDatePickerDialog = false
//                showTimePickerDialog = false
//            },
//            calendar.get(Calendar.HOUR_OF_DAY),
//            calendar.get(Calendar.MINUTE),
//            true
//        ).show()
//    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(name = if (glucosaId == null) "Registrar" else "Editar") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ColorButton
                ),
                navigationIcon = {
                    showDatePickerDialog = false
                    showTimePickerDialog = false
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
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
//                Text(
//                    SimpleDateFormat(
//                        "yyyy-MM-dd HH:mm",
//                        Locale.getDefault()
//                    ).format(existingGlucosa?.let { Date(it.tiempo_glucosa) }),
//                )

                Text(
                    if (glucosaId == null) "Registrar Nivel de Glucosa" else "Editar Nivel de Glucosa",
                    style = MaterialTheme.typography.titleLarge
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
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
                        modifier = Modifier.width(100.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Checkbox(
                            checked = useMmol,
                            onCheckedChange = { useMmol = it }
                        )
                        Text(text = "¿Tu glucometro\nmide en mmol/L?")
                    }
                }

                Row(modifier = Modifier
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
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).format(Date(fechaHora)), style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                var bottomSheetVisible by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .clickable { bottomSheetVisible = true }
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
                        selectedOption,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                if (bottomSheetVisible) {
                    ModalBottomSheet(onDismissRequest = { bottomSheetVisible = false }) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Momento de medición",
                                style = MaterialTheme.typography.titleLarge
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
                        onValueChange = {
                            notaGlucosa = it
                        },
                        label = { Text("Nota") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                    )
                }



                BotonPrincipal(
                    name = if (glucosaId == null) "Registrar" else "Actualizar",
                    backBrush = Brush.linearGradient(
                        colors = listOf(
                            ColorButton,
                            AzulOscuro
                        )
                    ),
                    color = Color.White
                ) {
                    if (momentoGlucosa == "Momento de medición") {
                        momentoGlucosa = ""
                    }

                    if (valorGlucosa.isNotBlank()) {
                        momentoGlucosa = selectedOption
                        val valorGlucosaFinal =
                            if (useMmol) valorGlucosa.toFloat() * 18f else valorGlucosa.toFloat()

                        val nuevaGlucosa = Glucosa(
                            id_glucosa = existingGlucosa?.id_glucosa ?: 0,
                            tiempo_glucosa = fechaHora,
                            momento_glucosa = momentoGlucosa,
                            valor_glucosa = valorGlucosaFinal,
                            peso_glucosa = pesoGlucosa.toFloat(),
                            nota_glucosa = notaGlucosa,
                            id_usuario = usuarioId
                        )

                        if (glucosaId == null) {
                            glucosaViewModel.insertarGlucosa(nuevaGlucosa)
                            Toast.makeText(context, "Registrado exitosamente", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            glucosaViewModel.actualizarGlucosa(nuevaGlucosa)
                            Toast.makeText(context, "Actualizado exitosamente", Toast.LENGTH_SHORT)
                                .show()
                        }

                        navController.popBackStack() // Regresar a la lista después de la operación
                    } else {
                        Toast.makeText(context, "Llene el campo de la glucosa", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    )
}