package com.example.githubclient.ui.userlist.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubclient.domain.model.User

@Composable
fun UserList(users: List<User>, onUserClick: (String) -> Unit, onLoadMoreClick: () -> Unit, isLastPage: Boolean) {
        LazyColumn(modifier = Modifier.padding(0.dp,24.dp)) {
            itemsIndexed(users) { index, user ->
                UserListItem(
                    user,
                    onClick = onUserClick,
                    isLastItem = index == users.size - 1,
                    onLoadMoreClick = onLoadMoreClick,
                    showLoadMore = !isLastPage
                )
            }
        }
}
