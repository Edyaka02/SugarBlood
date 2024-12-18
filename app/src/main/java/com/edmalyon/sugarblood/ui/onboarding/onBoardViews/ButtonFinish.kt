package com.edmalyon.sugarblood.ui.onboarding.onBoardViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edmalyon.sugarblood.ui.onboarding.dataStore.StoreBoarding
import androidx.compose.runtime.Composable

@Composable
fun ButtonFinish(currentPage: Int, navController: NavController, store: StoreBoarding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement =
        if (currentPage != 2 )
            Arrangement.SpaceBetween
        else
            Arrangement.Center
    ) {
        if ( currentPage == 2 ){
            OutlinedButton(
                onClick = {
                    navController.navigate("login"){
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            ) {
                Text(
                    text = "Comenzar!",
                    modifier = Modifier
                        .padding(
                            horizontal = 40.dp,
                            vertical = 8.dp
                        ),
                    color = Color.Gray
                )

            }
        }
    }
}