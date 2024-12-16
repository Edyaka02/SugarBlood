package com.edmalyon.sugarblood.ui.screens.glucosa

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.viewModels.GlucosaViewModel
import com.edmalyon.sugarblood.ui.theme.ColorButton
import com.edmalyon.sugarblood.ui.theme.ColorPrimario
import com.edmalyon.sugarblood.ui.theme.ColorSecondario
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaGlucosaScreen(
    navController: NavController,
    glucosaViewModel: GlucosaViewModel, //= hiltViewModel(),
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
            CenterAlignedTopAppBar(
                title = { TitleBar(name = "Registros de glucosa") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ColorButton
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("registrarGlucosa/$usuarioId") },
                containerColor = ColorButton,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Agregar Registro"
                )
            }
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
                    // Si la lista de glucosa está vacía, mostramos un mensaje
                    if (glucosaList.isEmpty()) {
                        item {
                            Text(
                                text = "No hay registros de glucosa.",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        // Si hay registros, los mostramos
                        items(glucosaList) { glucosa ->
                            GlucosaItem(glucosa)
                        }
                    }
                }
            }
        }

    )

}

@Composable
fun GlucosaItem(glucosa: Glucosa) {
    // Mostrar cada registro de glucosa en una fila
    var fecha = convertirMilisegundosAFecha(glucosa.tiempo_glucosa)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = ColorSecondario
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
                    text = "Nivel de Glucosa: ${glucosa.valor_glucosa}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Fecha: $fecha",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Puedes agregar más detalles aquí, como un ícono de editar o eliminar
            IconButton(
                onClick = { /* Acción para editar o eliminar */ },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
            }
        }
    }
}

fun convertirMilisegundosAFecha(milisegundos: Long): String {
    val date = Date(milisegundos)
    val dateFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return dateFormat.format(date)
}