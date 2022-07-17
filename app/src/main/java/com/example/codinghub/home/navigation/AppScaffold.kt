package com.example.codinghub.home.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.navigation.home.HomeNavGraph
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppScaffold(
    navController: NavController,
    authViewModel: AuthViewModel,
    coroutineScope: CoroutineScope,
    logout: ()-> Unit

) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        scaffoldState = scaffoldState,

        ) {

        HomeNavGraph(navController = navController, authViewModel = authViewModel,coroutineScope, logout = logout )

    }

}