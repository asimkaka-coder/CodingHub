package com.example.codinghub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.main.NotificationMessage
import com.example.codinghub.navigation.Navigation
import com.example.codinghub.ui.theme.CodingHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                    codingHub()
                }
            }
        }
    }
}



@Composable
fun codingHub() {

    val authViewModel = hiltViewModel<AuthViewModel>()
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    NotificationMessage(authViewModel = authViewModel)
    Navigation(
        navController = navController,
        authViewModel = authViewModel,
        coroutineScope = coroutineScope
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodingHubTheme {
        codingHub()
    }
}