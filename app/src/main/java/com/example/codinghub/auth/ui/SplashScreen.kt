import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codinghub.R
import com.example.codinghub.auth.ui.AuthViewModel
import com.example.codinghub.main.CheckUserSignedIn
import com.example.codinghub.navigation.DestinationScreen
import kotlinx.coroutines.delay




@Composable
fun SplashScreen(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {


    val scale = remember {
        Animatable(0f)
    }

//    CheckUserSignedIn(viewModel = authViewModel, navController = navController)
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(3000L)



        navController.navigate(DestinationScreen.SignInScreen.route){
            popUpTo(DestinationScreen.SplashScreen.route){
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo_app), contentDescription =null)

    }

}