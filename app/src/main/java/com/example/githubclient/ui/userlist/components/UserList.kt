package com.example.githubclient.ui.userlist.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.githubclient.domain.model.User
import com.example.githubclient.ui.navigation.UserDetailRoute

@Composable
fun UserList(users: List<User>, onUserClick: (String) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserListItem(
                user,
                onClick = onUserClick
            )
            HorizontalDivider()
        }
    }
}
