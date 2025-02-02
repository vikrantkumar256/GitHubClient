package com.example.githubclient.domain.repository

import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail

/**
 * Repository interface for fetching GitHub user-related data.
 * Implementations of this interface provide access to user details, repositories, and user lists.
 */
interface UserRepository {
    suspend fun getUsers(startId: Int, perPage: Int) : List<User>
    suspend fun getUserDetail(userName: String) : UserDetail
    suspend fun getUserRepos(userName: String) : List<Repo>
}