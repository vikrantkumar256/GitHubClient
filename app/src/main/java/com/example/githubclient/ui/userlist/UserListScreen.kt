package com.example.githubclient.ui.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import com.example.githubclient.ui.common.ErrorScreen
import com.example.githubclient.ui.common.LoadingScreen
import com.example.githubclient.ui.userlist.components.UserList
import com.example.githubclient.ui.userlist.state.UserListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserListViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("GitHub Users") }
            )
        },
    ) {
        innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
            when (uiState) {
                is UserListState.Loading -> {
                    LoadingScreen()
                }
                is UserListState.Success -> UserList(users = (uiState as UserListState.Success).users)
                is UserListState.Error -> {
                    ErrorScreen(
                        message = (uiState as UserListState.Error).message,
                        onRetry = { viewModel.loadUsers() }
                    )
                }
            }
        }
    }
}