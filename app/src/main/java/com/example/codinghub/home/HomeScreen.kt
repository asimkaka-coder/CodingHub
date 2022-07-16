import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.navigation.DestinationScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    coroutineScope: CoroutineScope
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            text = authViewModel.userDataVM.value?.fullName.toString(),
        modifier = Modifier.clickable {
            authViewModel.signOut()
            navController.navigate(DestinationScreen.SignInScreen.route){
                popUpTo(0)
            }
        }
            )

    }
}