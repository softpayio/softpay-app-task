package io.softpay.softpos.presentation.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.softpay.softpos.domain.model.PurchaseModel
import io.softpay.softpos.presentation.splash.SplashScreen
import io.softpay.softpos.presentation.starter.StartPaymentScreen
import io.softpay.softpos.presentation.store.StoreScreen
import io.softpay.softpos.presentation.transaction.TransactionScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
    ) {
        composable(
            route = Screens.SplashScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                ) +
                        fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(400))
            }
        ) {
            SplashScreen(
                navigateToFirstPage = {
                    navController.navigate(Screens.StartPaymentScreen.route) {
                        popUpTo(Screens.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screens.StartPaymentScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300))
            }) {
            StartPaymentScreen(
                navController = navController
            )
        }

        composable(route = Screens.TransactionScreen.route,
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300))
            }) {
            TransactionScreen(
                navController = navController,
            )
        }





    }
}