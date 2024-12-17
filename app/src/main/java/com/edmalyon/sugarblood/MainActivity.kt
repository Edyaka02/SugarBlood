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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edmalyon.sugarblood.navigation.AppHost
import com.edmalyon.sugarblood.ui.screens.glucosa.ListaGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.glucosa.RegistrarGlucosaScreen
import com.edmalyon.sugarblood.ui.screens.usuario.LoginUsuarioScreen
import com.edmalyon.sugarblood.ui.screens.usuario.RegistrarUsuarioScreen
import com.edmalyon.sugarblood.ui.theme.SugarBloodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Crea un NavController

        setContent {
            SugarBloodTheme {
                val navController = rememberNavController()
                //RegistrarUsuarioScreen()
                //LoginUsuarioScreen()
                AppHost(navController)
            }
        }
    }
}