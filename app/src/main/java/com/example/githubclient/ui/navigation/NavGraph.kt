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
object UserListRoute

@Serializable
data class UserDetailRoute(val userName: String)

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = UserListRoute)
    {
        composable<UserListRoute> {
            UserListScreen(
                onUserClick = {userName -> navController.navigate(UserDetailRoute(userName))}
            )
        }

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