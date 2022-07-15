package io.softpay.softpos.presentation.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.softpay.softpos.R
import io.softpay.softpos.presentation.animation.fadeInScreen
import io.softpay.softpos.theme.TextFieldBackground
import io.softpay.softpos.utils.formatAmount

@Preview
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TextFieldWithIcons(amount: Long = 0L, visible: Boolean = true) = fadeInScreen(visible) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 42.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.enter_amount),
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )
        }
        Row {
            OutlinedTextField(
                value = amount.formatAmount(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .border(
                        width = 1.dp,
                        brush = TextFieldBackground,
                        shape = RoundedCornerShape(12.dp)
                    ),
                textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                leadingIcon = {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "amount"
                    )
                },
                onValueChange = {},
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = "0") },
            )
        }
    }


}