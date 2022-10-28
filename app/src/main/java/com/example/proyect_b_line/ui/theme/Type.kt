package com.example.proyect_b_line.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyect_b_line.R

val gilroyFont= FontFamily(
    Font(R.font.gilroy_extrabold, FontWeight.ExtraBold),
    Font(R.font.gilroy_ligth,FontWeight.Light)
)
// Set of Material typography styles to start with
val TypographyWhite = Typography(
    bodyLarge = TextStyle(
        fontFamily = gilroyFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = blackText
    ),
    bodyMedium = TextStyle(
        fontFamily = gilroyFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.5.sp,
        color = blackText
    )
)
val TypographyBlack = Typography(
    bodyLarge = TextStyle(
        fontFamily = gilroyFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = whiteText
    ),
    bodyMedium = TextStyle(
        fontFamily = gilroyFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.5.sp,
        color = whiteText
    )

)
