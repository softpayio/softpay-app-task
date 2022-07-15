package io.softpay.softpos.presentation.component

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.softpay.softpos.presentation.animation.fadeInScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressView(visible: Boolean) = fadeInScreen(visible) {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(
                start = 24.dp,
                top = 20.dp,
                end = 20.dp,
                bottom = 4.dp)
            .width(80.dp)
            .height(80.dp)

    )
}



