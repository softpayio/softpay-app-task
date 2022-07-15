package io.softpay.softpos.presentation.animation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

@Composable
@ExperimentalAnimationApi
fun fadeInScreen(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    return AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit =fadeOut()
    ) { content() }
}