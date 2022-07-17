package com.example.codinghub

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.auth.ui.SignInScreen
import com.example.codinghub.auth.ui.SignupScreen
import com.example.codinghub.home.navigation.AppScaffold
import com.example.codinghub.home.navigation.BottomNavigationBar
import com.example.codinghub.main.NotificationMessage
import com.example.codinghub.navigation.DestinationScreen
import com.example.codinghub.navigation.Graph
import com.example.codinghub.navigation.Navigation
import com.example.codinghub.ui.theme.CodingHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                    codingHub(authViewModel)
                }
            }
        }
    }
}



@Composable
fun codingHub(authViewModel:AuthViewModel) {


    val navController = rememberNavController()
    val navBarController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    NotificationMessage(authViewModel = authViewModel)
//    Navigation(
//        navController = navController,
//        authViewModel = authViewModel,
//        coroutineScope = coroutineScope
//    )
    NavHost(navController = navController, startDestination =DestinationScreen.SplashScreen.route ){
        composable(DestinationScreen.SplashScreen.route){
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(DestinationScreen.SignUpScreen.route){
            SignupScreen(navController = navController, authViewModel =authViewModel,coroutineScope )
        }
        composable(DestinationScreen.SignInScreen.route){
            SignInScreen(navController = navController, authViewModel = authViewModel,coroutineScope)
        }

        composable(Graph.SCAFFOLD){
            AppScaffold(navController = navBarController, authViewModel = authViewModel,coroutineScope){
                navController.navigate(DestinationScreen.SignInScreen.route){
                    popUpTo(0)
                }
            }
        }

    }
}




//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CodingHubTheme {
//        codingHub()
//    }
//}