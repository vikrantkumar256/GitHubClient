package com.example.githubclient.ui.userlist.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.githubclient.domain.model.User

@Composable
fun UserList(users: List<User>, onUserClick: (String) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserListItem(
                user,
                onClick = onUserClick
            )
        }
    }
}
