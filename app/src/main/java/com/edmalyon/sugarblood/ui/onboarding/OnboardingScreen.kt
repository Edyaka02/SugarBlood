// OnboardingScreen.kt
package com.edmalyon.sugarblood.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.edmalyon.sugarblood.MainActivity
import com.edmalyon.sugarblood.R

class OnboardingActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("onboarding", Context.MODE_PRIVATE)

        if (isFirstTimeLaunch()) {
            setContent {
                OnboardingScreen {
                    markOnboardingComplete()
                    navigateToMain()
                }
            }
        } else {
            navigateToMain()
        }
    }

    private fun isFirstTimeLaunch(): Boolean {
        return sharedPreferences.getBoolean("first_time_launch", true)
    }

    private fun markOnboardingComplete() {
        sharedPreferences.edit().putBoolean("first_time_launch", false).apply()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    var pageIndex by remember { mutableStateOf(0) }

    val pages = listOf(
        OnboardingPage(R.drawable.logo_con_letras, "Bienvenido a nuestra app!", "Descripción de la página 1"),
        OnboardingPage(R.drawable.glucosa, "Mantén el control de tu glucosa", "Descripción de la página 2"),
        OnboardingPage(R.drawable.logo_con_letras, "Comienza ahora", "Descripción de la página 3")
    )

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = pages[pageIndex].imageRes),
                contentDescription = null,
                modifier = Modifier.size(200.dp).padding(bottom = 24.dp)
            )
            BasicText(text = pages[pageIndex].title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            BasicText(text = pages[pageIndex].description, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                if (pageIndex < pages.size - 1) {
                    pageIndex++
                } else {
                    onFinish()
                }
            }) {
                BasicText(text = if (pageIndex < pages.size - 1) "Siguiente" else "Comenzar")
            }
        }
    }
}

data class OnboardingPage(val imageRes: Int, val title: String, val description: String)
