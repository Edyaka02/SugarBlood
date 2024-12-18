package com.edmalyon.sugarblood.ui.viewModels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.edmalyon.sugarblood.R
import com.edmalyon.sugarblood.ui.onboarding.data.PageData
import com.edmalyon.sugarblood.ui.onboarding.dataStore.StoreBoarding
import com.edmalyon.sugarblood.ui.onboarding.onBoardViews.OnBoardingPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding){
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.drawable.logo_con_letras,
            "Bienvenido",
            "Bienvenido a la app de onBoarding"
        )
    )

    items.add(
        PageData(
            R.drawable.logo_con_letras,
            "Uso de onBoarding",
            "Mostrando una descripcion en la pagina 2"
        )
    )

    items.add(
        PageData(
            R.drawable.logo_con_letras,
            "Despedida",
            "Pagina final del onBoarding"
        )
    )

    items.add(
        PageData(
            R.drawable.logo_con_letras,
            "Otra página",
            "Esta es otra página agregada :D"
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        navController,
        store
    )
}