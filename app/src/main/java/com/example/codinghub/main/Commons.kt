package com.example.codinghub.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.navigation.DestinationScreen

@Composable
fun NotificationMessage(authViewModel: AuthViewModel) {
    val notifState = authViewModel.popUpNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun CommonProgressSpinner() {
    Row(
        modifier = Modifier
            .alpha(0.7f)
            .background(Color.LightGray)
            .clickable(false) { }
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CheckUserSignedIn(
    viewModel: AuthViewModel,
    navController: NavController
){
    val alreadySignedIn = remember{ mutableStateOf(false)}
    val loggedIn = viewModel.signedIn.value
    if(loggedIn && !alreadySignedIn.value){
        alreadySignedIn.value = true
        navController.navigate(DestinationScreen.HomeScreen.route){
            popUpTo(0)
        }
    }
}

fun navigateTo(navController: NavController, dest:DestinationScreen) {
    navController.navigate(dest.route){
        popUpTo(dest.route)
        launchSingleTop = true
    }
}

