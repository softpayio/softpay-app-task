package io.softpay.softpos.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val keyboardBackground = Brush.verticalGradient(
    colors = listOf(
        Primary,
        PrimaryDark
    )
)

val TextFieldBackground = Brush.verticalGradient(
    colors = listOf(
        White,
        Purple500
    )
)