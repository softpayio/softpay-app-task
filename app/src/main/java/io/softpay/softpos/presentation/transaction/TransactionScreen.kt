package io.softpay.softpos.presentation.transaction

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.softpay.softpos.R
import io.softpay.softpos.presentation.animation.slideInScreen
import io.softpay.softpos.presentation.component.Keyboard
import io.softpay.softpos.presentation.component.ProgressView
import io.softpay.softpos.presentation.component.TextFieldWithIcons
import io.softpay.softpos.presentation.navigation.Screens
import io.softpay.softpos.presentation.store.ConfirmScreen
import io.softpay.softpos.presentation.store.StoreScreen
import io.softpay.softpos.theme.Primary
import io.softpay.softpos.theme.PrimaryDark
import io.softpay.softpos.theme.White

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransactionScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<TransactionViewModel>()
    val state by viewModel.state().collectAsState()

    val backHandler = {
        viewModel.cancelTransaction()
        navController.navigate(Screens.StartPaymentScreen.route) {
            popUpTo(Screens.StartPaymentScreen.route) {
                inclusive = true
            }
        }
    }

    BackHandler {
        backHandler.invoke()
    }

    Surface(color = Primary, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            slideInScreen(visible = state.isWaitingAmount) {
                Keyboard(
                    onClick = { digit -> viewModel.enterNumber(digit) },
                    onConfirm = { viewModel.confirm() },
                    onDelete = { viewModel.deletedNumber() },
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            IconButton(
                onClick = {
                    backHandler.invoke()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .then(Modifier.size(30.dp))
                    .border(1.dp, PrimaryDark, shape = RoundedCornerShape(15.dp))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = White
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        }
        Row {
            Surface(
                color = PrimaryDark,
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(50.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextFieldWithIcons(
                        viewModel.amountValue,
                        visible = (!state.isProcessing && (state.isWaitingAmount || state.isAwaitingConfirmation))
                    )
                    ConfirmScreen(
                        onConfirm = {
                            viewModel.confirmPurchase(true)
                        },
                        onCancel = {
                            viewModel.confirmPurchase(false)
                            backHandler.invoke()
                        },
                        store = viewModel.storeDetails,
                        visible = (!state.isProcessing && state.isAwaitingConfirmation),
                    )
                    StoreScreen(
                        visible = (!state.isProcessing && state.isSuccess),
                        parPurchaseModel = viewModel.purchaseModel
                    )
                    ProgressView(visible = state.isProcessing)

                }
            }
        }
    }

}


