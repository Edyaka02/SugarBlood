package com.edmalyon.sugarblood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edmalyon.sugarblood.ui.screens.glucosa.ListaGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.RegistrarGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.usuario.RegistrarUsuarioScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tu contenido Compose
            //UsuarioScreen()
            //RegistrarUsuarioScreen()
            // Variable para controlar qué pantalla mostrar
            var mostrarRegistro by remember { mutableStateOf(true) }

            // Contenido de la app
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botones para alternar entre las pantallas
                Row(modifier = Modifier.padding(16.dp)) {
                    Button(onClick = { mostrarRegistro = true }) {
                        Text("Registrar Glucosa")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { mostrarRegistro = false }) {
                        Text("Lista de Glucosa")
                    }
                }

                // Condición para mostrar la pantalla correcta
                if (mostrarRegistro) {
                    RegistrarGlucosaScreen(usuarioId = 1)
                } else {
                    ListaGlucosaScreen(usuarioId = 1)
                }
            }

        }
    }
}