import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.edmalyon.sugarblood.R
import com.edmalyon.sugarblood.ui.onboarding.dataStore.StoreBoarding
import kotlinx.coroutines.delay
import android.util.Log

@Composable
fun SplashScreen(navController: NavController, store: StoreBoarding) {
    val isFirstTime = store.getStoreBoarding.collectAsState(initial = false).value
    Log.d("SplashScreen", "Is first time: $isFirstTime")

    LaunchedEffect(isFirstTime) {
        delay(3000) // 3 seconds
        if (!isFirstTime) {
            navController.navigate("onboarding") {
                popUpTo("splash") { inclusive = true }
            }
            // Save the state to indicate that onboarding has been shown
            store.saveBoarding(true)
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_con_letras),
            contentDescription = null
        )
    }
}