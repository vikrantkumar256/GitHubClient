package com.example.githubclient.ui.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubclient.ui.common.ErrorScreen
import com.example.githubclient.ui.common.LoadingScreen
import com.example.githubclient.ui.theme.LightBlueGray
import com.example.githubclient.ui.userlist.components.UserList
import com.example.githubclient.ui.userlist.state.UserListState

/*
 * UserListScreen Composable:
 * Displays a screen showing a list of GitHub users with a top app bar and loading/error states.
 * Fetches and displays user data using a ViewModel, with pagination support for loading more users.
 * Handles navigation to user details when a user item is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserListViewModel = hiltViewModel(), onUserClick: (String) -> Unit) {

    // Collects UI state and total users displayed from the ViewModel
    val uiState by viewModel.uiState.collectAsState()
    val totalUserDisplayed by viewModel.totalUserDisplayed.collectAsState()

    // Scaffold to define the main layout structure including the top bar
    Scaffold(
        topBar = {
            // Defines the top app bar
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = { Text("GitHub Users", style = MaterialTheme.typography.titleLarge) },
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary
                    ),
            )
        },
        containerColor = LightBlueGray
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // Handle different UI states: Loading, Success, Error
            when (uiState) {
                is UserListState.Loading -> LoadingScreen()
                is UserListState.Success -> {

                    // Displays the list of users, limiting to the number of users to be shown
                    val users = (uiState as UserListState.Success).users.take(totalUserDisplayed)
                    UserList(users = users, onUserClick = onUserClick, onLoadMoreClick = { viewModel.loadMoreUsers() }, isLastPage = (viewModel.usersFetchLimit == totalUserDisplayed))
                }
                is UserListState.Error -> ErrorScreen(
                    message = (uiState as UserListState.Error).message,
                    onRetry = { viewModel.loadUsers() }
                )
            }
        }
    }
}
