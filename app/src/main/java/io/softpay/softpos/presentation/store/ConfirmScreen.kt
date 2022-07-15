package io.softpay.softpos.presentation.store

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.softpay.sdk.Store
import io.softpay.softpos.R
import io.softpay.softpos.presentation.animation.fadeInScreen
import io.softpay.softpos.presentation.component.ImageButton
import io.softpay.softpos.theme.White

@ExperimentalAnimationApi
@Composable
fun ConfirmScreen(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    visible: Boolean,
    store: Store?
) {
    fadeInScreen(visible = visible) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Text(
                    color = White,
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.confirm_shopping_text)
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
            Row {

                Text(color = White, text = store?.name ?: "")
            }
            Row {
                Text(color = White, text = store?.address ?: "")
            }
            Row {
                Text(color = White, text = store?.postalCode ?: "")
            }
            Row {
                Spacer(modifier = Modifier.height(24.dp))
                ImageButton(icon = Icons.Rounded.Check, onClick = onConfirm)
                ImageButton(icon = Icons.Rounded.Close, onClick = onCancel)
            }
        }
    }
}
