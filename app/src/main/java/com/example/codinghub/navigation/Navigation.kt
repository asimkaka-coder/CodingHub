package com.example.codinghub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.navigation.auth.authNavGraph
import com.example.codinghub.navigation.home.homeNavGraph
import kotlinx.coroutines.CoroutineScope


@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    coroutineScope: CoroutineScope
) {

    NavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION
    ) {

        authNavGraph(
            navController = navController,
            authViewModel = authViewModel,
            coroutineScope = coroutineScope
        )

        homeNavGraph(
            navController = navController,
            authViewModel = authViewModel,
            coroutineScope = coroutineScope
        )

    }
}