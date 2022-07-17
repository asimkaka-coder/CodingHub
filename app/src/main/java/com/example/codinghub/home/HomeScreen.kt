import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.home.navigation.BottomNavigationBar
import com.example.codinghub.navigation.DestinationScreen
import com.example.codinghub.navigation.home.HomeNavGraph
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home",
            fontSize = 40.sp
        )
    }
}
