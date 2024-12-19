package com.edmalyon.sugarblood.ui.screens.glucosa

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edmalyon.sugarblood.R
import com.edmalyon.sugarblood.components.BotonPrincipal
import com.edmalyon.sugarblood.components.CustomFloatingActionButton
import com.edmalyon.sugarblood.components.CustomTopAppBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.Color1
import com.edmalyon.sugarblood.ui.theme.Color2
import com.edmalyon.sugarblood.ui.theme.Color3
import java.util.Date
import java.util.Locale

@Composable
fun ListaGlucosaScreen(
    navController: NavController,
    glucosaViewModel: GlucosaViewModel,
    usuarioId: Int
) {
    // Cargar los niveles de glucosa del usuario
    LaunchedEffect(Unit) {
        glucosaViewModel.obtenerGlucosaPorUsuario(usuarioId)
    }

    // Obtenemos la lista de glucosa desde el ViewModel
    val glucosaList by glucosaViewModel.glucosaList


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Registros",
                backBrush = Brush.linearGradient(
                    colors = listOf(
                        Color1,
                        Color2
                    )
                ),
                color = Color.White
            )
        },
        floatingActionButton = {

            CustomFloatingActionButton(
                onClick = { navController.navigate("registrarGlucosa/$usuarioId") },
                backBrush = Brush.linearGradient(
                    colors = listOf(
                        Color1,
                        Color3
                    )
                ),
                contentColor = Color.White,
                icon = Icons.Default.Add,
                contentDescription = ""
            )

        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
            ) {
                //Text("Historial de Glucosa", style = MaterialTheme.typography.titleMedium)

                // Mostrar los niveles de glucosa usando LazyColumn
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {

                    if (glucosaList.isEmpty()) {
                        item {

                            Image(
                                painter = painterResource(id = R.drawable.registros),
                                contentDescription = "No hay registros de glucosa",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .size(400.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "No hay registros de glucosa.",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        val glucosaListOrdenada = glucosaList.sortedByDescending { it.tiempo_glucosa }
                        items(glucosaListOrdenada) { glucosa ->
                            GlucosaItem(
                                glucosa,
                                onEdit = { glucosaAEditar ->
                                    navController.navigate("editarGlucosa/${glucosaAEditar.id_glucosa}")
                                },
                                onDelete = { glucosaAEliminar ->
                                    glucosaViewModel.eliminarGlucosa(glucosaAEliminar)
                                }
                            )
                        }
                    }
                }
            }
        }

    )

}

@Composable
fun GlucosaItem(glucosa: Glucosa, onEdit: (Glucosa) -> Unit, onDelete: (Glucosa) -> Unit) {
    val fecha = convertirMilisegundosAFecha(glucosa.tiempo_glucosa)
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar Eliminación") },
            text = { Text("¿Está seguro de que desea eliminar este registro?") },
            containerColor = Color.White,
            confirmButton = {

                BotonPrincipal(
                    name = "Eliminar",
                    backBrush = Brush.linearGradient(
                        colors = listOf(
                            Color(188, 58, 58),
                            Color(253, 29, 29),
                            Color(252, 152, 69)
                        )
                    ),
                    color = Color.White
                ) {
                    onDelete(glucosa)
                    showDialog = false
                }
            },
            dismissButton = {

                BotonPrincipal(
                    name = "Cancelar",
                    backBrush = Brush.linearGradient(
                        colors = listOf(
                            Color1,
                            Color3
                        )
                    ),
                    color = Color.White
                ) {
                    showDialog = false
                }
            }
        )
    }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                glucosa.valor_glucosa < 70 -> Color(252, 164, 126)// Nivel bajo
                glucosa.valor_glucosa in 70.0..140.0 -> Color(176, 242, 180) // Nivel normal
                else -> Color(245, 61, 88) // Nivel alto
            },
            contentColor = when {
                glucosa.valor_glucosa < 70 -> Color.Black
                glucosa.valor_glucosa in 70.0..140.0 -> Color.DarkGray
                else -> Color.White
            }
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Glucosa: ${glucosa.valor_glucosa} mg/dL",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Fecha: $fecha",
                    style = MaterialTheme.typography.bodySmall,
                    color = when {
                        glucosa.valor_glucosa < 70 -> Color.Black
                        glucosa.valor_glucosa in 70.0..140.0 -> Color.DarkGray
                        else -> Color.White
                    }
                )
            }

            Row {
                IconButton(
                    onClick = { onEdit(glucosa) },
                    modifier = Modifier.padding(start = 30.dp)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}

fun convertirMilisegundosAFecha(milisegundos: Long): String {
    val date = Date(milisegundos)
    val dateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date)
}