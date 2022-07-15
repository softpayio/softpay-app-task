package io.softpay.softpos.presentation.starter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.softpay.softpos.R
import io.softpay.softpos.presentation.navigation.Screens
import io.softpay.softpos.theme.*

@Composable
fun StartPaymentScreen(navController: NavController) {
    BackgroundCard(navController)
    MainCard()
}

@Composable
fun BackgroundCard(navController: NavController) {
    Surface(color = Primary, modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = Modifier.width(200.dp).height(54.dp),
                onClick = {
                    navController.navigate(Screens.TransactionScreen.route)
                },
                enabled = true,
                shape = RoundedCornerShape(23.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = TransparentLight
                )
            ) {
                Text(text = stringResource(id = R.string.start), color = PrimaryDark)
                Icon(
                    tint = PrimaryDark,
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.padding(25.dp))

        }


    }
}

@Composable
fun MainCard() {
    val modifier = Modifier.padding(4.dp)
    Box {
        Surface(
            color = White,
            modifier = Modifier
                .height(600.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(60.dp).copy(
                topEnd = ZeroCornerSize,
                topStart = ZeroCornerSize
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .width(400.dp)
                        .height(450.dp),
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = stringResource(id = R.string.app_image_poster)
                )
                Spacer(modifier = modifier)

                Text(
                    text = stringResource(id = R.string.company_slogan),
                    fontSize = 18.sp,
                    color = darkSlateGray,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = modifier)

                Text(
                    text = stringResource(id = R.string.company_description),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

