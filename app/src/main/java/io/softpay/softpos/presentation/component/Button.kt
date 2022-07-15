package io.softpay.softpos.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.softpay.softpos.theme.PrimaryDark
import io.softpay.softpos.theme.White


@Composable
fun Button(
    number: Int,
    onClick: (digit: Int) -> Unit,
) {
    OutlinedButton(
        onClick = { onClick(number) },
        modifier = Modifier
            .size(60.dp)
            .aspectRatio(1f)
            .padding(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = PrimaryDark,
            contentColor = White
        ),
        shape = CircleShape,
    ) {
        Text(
            text = number.toString(),
            fontSize = 22.sp,
            color = White
        )
    }
}