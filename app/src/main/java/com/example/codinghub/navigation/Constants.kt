package com.example.codinghub.navigation

sealed class DestinationScreen(val route: String) {
    object SplashScreen : DestinationScreen("splash")
    object SignUpScreen : DestinationScreen("signup")
    object SignInScreen : DestinationScreen("signin")
    object HomeScreen : DestinationScreen("home")
    object ProfileScreen : DestinationScreen("profile")
    object SettingsScreen : DestinationScreen("settings")
    object SearchScreen : DestinationScreen("search")

}

object Graph {
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}