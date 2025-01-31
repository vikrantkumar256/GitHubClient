package com.example.githubclient.ui.userlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubclient.domain.model.User
import com.example.githubclient.ui.theme.GitHubClientTheme

@Composable
fun UserListScreen(viewModel: UserListViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        is UserListState.Loading -> {
            Text(text = "Loading", modifier = Modifier.padding(50.dp, 50.dp))
        }
        is UserListState.Success -> UserList(users = (uiState as UserListState.Success).users)
        is UserListState.Error -> {
            Text(text = "ErrorScreen", modifier = Modifier.padding(50.dp,50.dp))
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    Text(text = "success", modifier = Modifier.padding(50.dp, 50.dp))
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    GitHubClientTheme {
        UserListScreen()
    }
}