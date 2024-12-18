package com.edmalyon.sugarblood.ui.onboarding.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edmalyon.sugarblood.ui.onboarding.dataStore.StoreBoarding
import com.edmalyon.sugarblood.ui.viewModels.MainOnBoarding
import com.edmalyon.sugarblood.ui.views.HomeView

@Composable
fun NavManager(){
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getStoreBoarding.collectAsState(initial = false)

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination =
        if ( store.value == true )
            "home"
        else
            "onBoarding"
    ){
        composable("onBoarding"){
            MainOnBoarding(navController, dataStore)
        }

        composable("home"){
            HomeView(navController)
        }
    }
}