package io.softpay.softpos.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.softpay.softpos.theme.keyboardBackground

@Composable
fun Keyboard(
    onClick: (digit: Int) -> Unit,
    onConfirm: () -> Unit,
    onDelete: () -> Unit,
) {
    val modifier = Modifier
        .background(brush = keyboardBackground)
        .fillMaxWidth()
        .padding(2.dp)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Button(number = 1, onClick = onClick)
            Button(number = 2, onClick = onClick)
            Button(number = 3, onClick = onClick)
            Button(number = 4, onClick = onClick)
            Button(number = 5, onClick = onClick)
            Button(number = 6, onClick = onClick)
        }
        Row {
            Button(number = 7, onClick = onClick)
            Button(number = 8, onClick = onClick)
            Button(number = 9, onClick = onClick)
            Button(number = 0, onClick = onClick)
            ImageButton(icon = Icons.Rounded.Check, onConfirm, Color.White)
            ImageButton(icon = Icons.Rounded.ArrowBack, onDelete, Color.White)
        }
        Spacer(modifier = Modifier.padding(18.dp))
    }
}

