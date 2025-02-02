package com.example.githubclient.ui.userdetail.state

import com.example.githubclient.domain.model.UserDetail

sealed class UserDetailState {
    data object Loading : UserDetailState()
    data class Success(val userDetail: UserDetail) : UserDetailState()
    data class Error(val message: String) : UserDetailState()
}