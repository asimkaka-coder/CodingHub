package com.example.codinghub.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codinghub.R
import com.example.codinghub.auth.ui.components.CustomTextField
import com.example.codinghub.main.CheckUserSignedIn
import com.example.codinghub.main.CommonProgressSpinner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SignupScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    coroutineScope: CoroutineScope
) {
    CheckUserSignedIn(viewModel = authViewModel, navController = navController)

    val focus = LocalFocusManager.current


    Box(modifier = Modifier.fillMaxSize()) {

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
                text = "Welcome!",
                modifier = Modifier.padding(8.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Sign Up to see coding stuff from your friends",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }

        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val usernameState = remember { mutableStateOf(TextFieldValue()) }
            val fullNameState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }



            CustomTextField(modifier = Modifier, label = "Email Address", stateValue = emailState)
            CustomTextField(modifier = Modifier, label = "Full Name", stateValue = fullNameState)
            CustomTextField(modifier = Modifier, label = "Username", stateValue = usernameState)

            CustomTextField(modifier = Modifier, label = "Password", stateValue = passwordState,true)


            Button(
                onClick = {
                    focus.clearFocus(true)
//                          navController.navigate("signin")
                    coroutineScope.launch {
                        authViewModel.signUpUser(
                            usernameState.value.text,
                            emailState.value.text,
                            passwordState.value.text,
                            fullName = fullNameState.value.text
                        )
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(230.dp)
            ) {
                Text(text = "Sign Up")
            }

        }


        if(authViewModel.inProgress.value){
            CommonProgressSpinner()
        }


        Text(
            buildAnnotatedString {
                append("By Signing Up, you agree to our ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Terms, Data Policy and Cookies.")
                }
            },
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.BottomCenter),
            fontSize = 14.sp,
        )
    }
}



