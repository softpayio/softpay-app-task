package io.softpay.softpos.presentation.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.softpay.softpos.R
import io.softpay.softpos.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToFirstPage: () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navigateToFirstPage.invoke()
    }

    SplashDesign(alpha = alphaAnimation.value)
}

@Composable
fun SplashDesign(alpha: Float) {
    Box {
        Surface(color = White, modifier = Modifier.fillMaxSize(),) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(300.dp)
                        .alpha(alpha = alpha),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(R.string.desc_softpay_logo),
                )
            }
        }
    }

}

