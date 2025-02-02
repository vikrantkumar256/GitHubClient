package com.example.githubclient.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.githubclient.ui.userdetail.UserDetailScreen
import com.example.githubclient.ui.userlist.UserListScreen
import kotlinx.serialization.Serializable


@Serializable
object UserListRoute // represent the route for user list screen

@Serializable
data class UserDetailRoute(val userName: String) // represent the route for user detail screen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    // Set up the navigation host with routes and screen composables
    NavHost(navController = navController, startDestination = UserListRoute)
    {
        // Navigate to UserDetailScreen when a user is clicked from the UserListScreen
        composable<UserListRoute> {
            UserListScreen(
                onUserClick = {userName -> navController.navigate(UserDetailRoute(userName))}
            )
        }

        // For the UserDetailScreen route,
        // extract the userName parameter and display the UserDetailScreen
        composable<UserDetailRoute> {
            backStackEntry ->
            val route = backStackEntry.toRoute<UserDetailRoute>()
            UserDetailScreen(
                userName = route.userName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}