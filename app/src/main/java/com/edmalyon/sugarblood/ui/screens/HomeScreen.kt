package com.edmalyon.sugarblood.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edmalyon.sugarblood.components.MainButton
import com.edmalyon.sugarblood.components.TitleBar
import com.edmalyon.sugarblood.components.TitleView

//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@Composable
//fun HomeScreen(//navController: NavController
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Bienvenido a SugarBlood",
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//        Button(
//            onClick = {
//                //navController.navigate("user_register")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        ) {
//            Text(
//                "Registrar Usuario"
//            )
//        }
//        Button(
//            onClick = {
//                //navController.navigate("user_screen/0")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Text("Ver Usuario")
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    usuarioId: Int
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(name = "Incio") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TitleView(name = "Pacientes")
                Spacer(modifier = Modifier.padding(10.dp))
                MainButton(name = "Ver pacientes", backColor = Color.Red, color = Color.White) {
                    //navController.navigate("login")
                }
                Spacer(modifier = Modifier.padding(10.dp))

                MainButton(
                    name = "Agregar un paciente",
                    backColor = Color.Red,
                    color = Color.White
                ) {
                    //navController.navigate("registrar")
                }

            }
        }
    )
}