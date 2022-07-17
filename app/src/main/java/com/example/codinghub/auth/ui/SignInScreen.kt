package com.example.codinghub.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.example.codinghub.R
import com.example.codinghub.auth.ui.components.CustomTextField
import com.example.codinghub.main.CheckUserSignedIn
import com.example.codinghub.main.CommonProgressSpinner
import com.example.codinghub.main.navigateTo
import com.example.codinghub.navigation.DestinationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    coroutineScope: CoroutineScope
) {


    CheckUserSignedIn(viewModel = authViewModel, navController = navController)
    val focus = LocalFocusManager.current
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_app), contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )

            Text(
                text = "Hello!",
                modifier = Modifier
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Login with your credentials",
                modifier = Modifier
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }


            CustomTextField(modifier = Modifier, label = "Email Address", stateValue = emailState)

            CustomTextField(
                modifier = Modifier,
                label = "Password",
                stateValue = passwordState,
                true
            )
            Text(
                text = "forgot password?",
                modifier = Modifier
                    .padding(start = 150.dp, 5.dp, bottom = 8.dp)

                    .clickable {

                    },
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp
            )


            Button(
                onClick = {
                    focus.clearFocus(true)
                    coroutineScope.launch {
                        authViewModel.signInUser(emailState.value.text, passwordState.value.text)
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(230.dp)
            ) {
                Text(text = "Sign In",
                style = MaterialTheme.typography.labelMedium)
            }

        }
        if (authViewModel.inProgress.value) {
            CommonProgressSpinner()
        }

        Text(
            buildAnnotatedString {
                append("Don't have an account? ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Sign up")
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    navigateTo(navController, DestinationScreen.SignUpScreen)
                },
            style = MaterialTheme.typography.bodySmall,
        )

    }
}

