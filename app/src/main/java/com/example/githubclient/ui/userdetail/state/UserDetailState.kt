package com.example.githubclient.ui.userdetail.state

import com.example.githubclient.domain.model.UserDetail

/**
 * Represents UserDetailState
 * - Loading: When data is being fetched.
 * - Success: When user details are successfully fetched.
 * - Error: When an error occurs while fetching user details.
 */
sealed class UserDetailState {
    data object Loading : UserDetailState()
    data class Success(val userDetail: UserDetail) : UserDetailState()
    data class Error(val message: String) : UserDetailState()
}