package com.example.githubclient.ui.userdetail.state

import com.example.githubclient.domain.model.Repo

/**
 * Represents the different states of loading user repositories:
 * - Loading: When repositories are being fetched.
 * - Success: When repositories are successfully fetched.
 * - Error: When an error occurs while fetching repositories.
 */
sealed class UserReposState {
    object Loading : UserReposState()
    data class Success(val repos: List<Repo>) : UserReposState()
    data class Error(val message: String) : UserReposState()
}