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
import com.example.githubclient.ui.userlist.components.UserList
import com.example.githubclient.ui.userlist.state.UserListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserListViewModel = hiltViewModel(), onUserClick: (String) -> Unit) {

    val uiState by viewModel.uiState.collectAsState()
    val totalUserDisplayed by viewModel.totalUserDisplayed.collectAsState()

    Scaffold(
        topBar = {
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
        containerColor = Color(0xFFE8EAF6)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is UserListState.Loading -> LoadingScreen()
                is UserListState.Success -> {
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
