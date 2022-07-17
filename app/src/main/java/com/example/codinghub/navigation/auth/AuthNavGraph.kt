package com.example.codinghub.navigation.auth

import SplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.auth.ui.SignInScreen
import com.example.codinghub.auth.ui.SignupScreen
import com.example.codinghub.navigation.DestinationScreen
import com.example.codinghub.navigation.Graph
import kotlinx.coroutines.CoroutineScope


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    coroutineScope:CoroutineScope
){

    navigation(
        startDestination = DestinationScreen.SplashScreen.route ,
        route = Graph.AUTHENTICATION
    ){

        composable(DestinationScreen.SplashScreen.route){
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(DestinationScreen.SignUpScreen.route){
            SignupScreen(navController = navController, authViewModel =authViewModel,coroutineScope )
        }
        composable(DestinationScreen.SignInScreen.route){
            SignInScreen(navController = navController, authViewModel = authViewModel,coroutineScope)
        }

    }



}