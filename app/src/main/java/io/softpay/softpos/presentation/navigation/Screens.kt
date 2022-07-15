package io.softpay.softpos.presentation.navigation

sealed class Screens(val route:String){
    object SplashScreen:Screens("splash_screen")
    object StartPaymentScreen:Screens("start_screen")
    object TransactionScreen:Screens("transaction_screen")
    object StoreScreen:Screens("store_screen/{pData}")
}