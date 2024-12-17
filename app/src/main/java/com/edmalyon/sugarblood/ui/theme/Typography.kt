package com.edmalyon.sugarblood.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.edmalyon.sugarblood.R

val Baloo2FontFamily = FontFamily(
    Font(R.font.baloo2_regular, FontWeight.Normal),
    Font(
        R.font.baloo2_bold,
        FontWeight.Bold
    )
)
val myTypography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(
            fontFamily = Baloo2FontFamily,
            fontWeight = FontWeight.Bold
        ),
        displayMedium = displayMedium.copy(
            fontFamily = Baloo2FontFamily,
            fontWeight = FontWeight.Bold
        ),
        displaySmall = displaySmall.copy(fontFamily = Baloo2FontFamily),
        headlineLarge = headlineLarge.copy(
            fontFamily = Baloo2FontFamily,
            fontWeight = FontWeight.Bold
        ),
        headlineMedium = headlineMedium.copy(fontFamily = Baloo2FontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = Baloo2FontFamily),
        titleLarge = titleLarge.copy(fontFamily = Baloo2FontFamily, fontWeight = FontWeight.Bold),
        titleMedium = titleMedium.copy(fontFamily = Baloo2FontFamily),
        titleSmall = titleSmall.copy(fontFamily = Baloo2FontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = Baloo2FontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = Baloo2FontFamily),
        bodySmall = bodySmall.copy(fontFamily = Baloo2FontFamily),
        labelLarge = labelLarge.copy(fontFamily = Baloo2FontFamily, fontWeight = FontWeight.Bold),
        labelMedium = labelMedium.copy(fontFamily = Baloo2FontFamily),
        labelSmall = labelSmall.copy(fontFamily = Baloo2FontFamily)
    )
}