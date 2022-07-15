package io.softpay.softpos.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isLandscape() = LocalConfiguration.current .orientation == Configuration.ORIENTATION_LANDSCAPE

fun Long.formatAmount():String{
    val amountAsString = "$this"
    val amountValue = when {
        amountAsString.length < 2 -> {
            "0,0$amountAsString"
        }
        amountAsString.length < 3 -> {
            "0,$amountAsString"
        }
        else -> {
            StringBuilder(amountAsString).insert(amountAsString.length - 2, ",").toString()
        }
    }
    return "$amountValue kr."
}
