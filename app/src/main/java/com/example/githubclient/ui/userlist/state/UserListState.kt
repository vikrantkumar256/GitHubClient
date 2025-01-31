package com.example.githubclient.ui.userlist.state

import com.example.githubclient.domain.model.User

sealed class UserListState {
    data object Loading : UserListState()
    data class Success(val users: List<User>) : UserListState()
    data class Error(val message: String) : UserListState()
}