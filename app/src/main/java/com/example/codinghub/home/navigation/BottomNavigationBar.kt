package com.example.codinghub.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codinghub.R
import com.example.codinghub.navigation.DestinationScreen


@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    NavigationBar() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.titleResId)
                    )
                },
                label = { Text(text = stringResource(id = item.titleResId)) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }

}


sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = DestinationScreen.HomeScreen.route,
        titleResId = R.string.screen_title_home,
        icon = Icons.Default.Home
    )

    object Search : BottomNavItem(
        route = DestinationScreen.SearchScreen.route,
        titleResId = R.string.screen_title_search,
        icon = Icons.Default.Search
    )

    object Profile : BottomNavItem(
        route = DestinationScreen.ProfileScreen.route,
        titleResId = R.string.screen_title_profile,
        icon = Icons.Default.AccountCircle
    )

    object Settings : BottomNavItem(
        route = DestinationScreen.SettingsScreen.route,
        titleResId = R.string.screen_title_settings,
        icon = Icons.Default.Settings
    )
}