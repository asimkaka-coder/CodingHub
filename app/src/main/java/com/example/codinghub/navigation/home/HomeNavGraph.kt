package com.example.codinghub.navigation.home

import HomeScreen
import ProfileScreen
import SettingsScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.home.SearchScreen
import com.example.codinghub.navigation.DestinationScreen
import com.example.codinghub.navigation.Graph
import kotlinx.coroutines.CoroutineScope


fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    coroutineScope: CoroutineScope
){

    navigation(
        startDestination = DestinationScreen.HomeScreen.route ,
        route = Graph.HOME
    ){

        composable(DestinationScreen.HomeScreen.route){
            HomeScreen(navController = navController, authViewModel = authViewModel, coroutineScope = coroutineScope)
        }
        composable(DestinationScreen.SearchScreen.route){
            SearchScreen(navController = navController, authViewModel =authViewModel,coroutineScope = coroutineScope )
        }
        composable(DestinationScreen.ProfileScreen.route){
            ProfileScreen(navController = navController, authViewModel = authViewModel,coroutineScope = coroutineScope)
        }

        composable(DestinationScreen.SettingsScreen.route){
            SettingsScreen(navController = navController, authViewModel = authViewModel,coroutineScope = coroutineScope)
        }

    }



}