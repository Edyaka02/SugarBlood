package com.edmalyon.sugarblood.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.edmalyon.sugarblood.R

object Imagenes {
    //val Logo: Painter @Composable get() = painterResource(id = R.drawable.logo)
    val UsuarioAvatar: Painter
        @Composable
        get() = painterResource(id = R.drawable.registro)

    val Logo_letras: Painter @Composable get() = painterResource(id = R.drawable.logo_con_letras)
    val Logo: Painter @Composable get() = painterResource(id = R.drawable.logo_sin_letras)


}