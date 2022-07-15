package io.softpay.softpos.presentation.store

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.softpay.softpos.domain.model.PurchaseModel
import io.softpay.softpos.presentation.animation.fadeInScreen
import io.softpay.softpos.theme.White

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StoreScreen(
    visible: Boolean,
    parPurchaseModel: PurchaseModel?
) {
    fadeInScreen(visible = visible) {
        Column(
            modifier = Modifier.padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row {
                Text(color = White, text = parPurchaseModel?.name ?: "")
            }
            Row {
                Text(color = White, text = parPurchaseModel?.address ?: "")
            }
            Row {
                Text(color = White, text = parPurchaseModel?.postalCode ?: "")
            }
            Row {
                Text(color = White, text = parPurchaseModel?.referenceId ?: "")
            }
            Row {
                Text(color = White, text = parPurchaseModel?.amount ?: "")
            }
        }
    }

}


