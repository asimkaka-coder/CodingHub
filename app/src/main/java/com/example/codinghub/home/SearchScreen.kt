package com.example.codinghub.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codinghub.auth.ui.AuthViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SearchScreen(

    navController: NavHostController,

) {

    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Search",
                fontSize = 40.sp
            )
        }

    }
}