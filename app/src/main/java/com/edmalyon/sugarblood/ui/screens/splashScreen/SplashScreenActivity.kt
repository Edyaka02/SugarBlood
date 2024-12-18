package com.edmalyon.sugarblood.ui.screens.splashScreen

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.edmalyon.sugarblood.ui.onboarding.dataStore.StoreBoarding

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val store = StoreBoarding(context)
            SplashScreen(navController, store)
        }
    }
}