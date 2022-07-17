import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.main.navigateTo
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProfileScreen(
    navController: NavHostController,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Profile",
                fontSize = 40.sp
            )
        }

    }
}