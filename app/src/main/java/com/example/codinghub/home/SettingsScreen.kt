import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.main.CheckUserSignedIn
import com.example.codinghub.main.navigateTo
import com.example.codinghub.navigation.DestinationScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun SettingsScreen(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    logout: ()-> Unit

    ) {
    val signedIn = authViewModel.signedIn.value

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Settings",
                fontSize = 40.sp
            )

            Button(
                onClick = {
                    if(signedIn) {
                        authViewModel.signOut()
                        logout()
                    }
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Logout")
            }
        }

    }
}