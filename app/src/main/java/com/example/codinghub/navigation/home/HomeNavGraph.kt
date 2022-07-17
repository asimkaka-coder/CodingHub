package com.example.codinghub.navigation.home

import HomeScreen
import ProfileScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.auth.ui.SignInScreen
import com.example.codinghub.home.Home
import com.example.codinghub.home.SearchScreen
import com.example.codinghub.navigation.DestinationScreen
import com.example.codinghub.navigation.Graph
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    coroutineScope:CoroutineScope,
    logout: ()-> Unit
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = DestinationScreen.HomeScreen.route ,
    ){

        composable(DestinationScreen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(DestinationScreen.SearchScreen.route){
            SearchScreen(navController = navController)
        }
        composable(DestinationScreen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }

        composable(DestinationScreen.SettingsScreen.route){
            SettingsScreen(navController = navController, authViewModel = authViewModel, logout = logout)
        }


    }



}