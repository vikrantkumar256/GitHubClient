package com.example.githubclient.ui.userdetail.state

import com.example.githubclient.domain.model.Repo

sealed class UserReposState {
    object Loading : UserReposState()
    data class Success(val repos: List<Repo>) : UserReposState()
    data class Error(val message: String) : UserReposState()
}