package io.softpay.softpos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.softpay.softpos.presentation.navigation.Navigation
import io.softpay.softpos.theme.SoftPayAppTheme

@AndroidEntryPoint
class TransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windows = this.window
        windows.statusBarColor = Color.White.toArgb()
        setContent {
            SoftPayAppTheme {
                Navigation()
            }
        }
    }
}
