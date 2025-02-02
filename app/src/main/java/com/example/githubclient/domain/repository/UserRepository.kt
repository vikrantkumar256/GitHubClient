package com.example.githubclient.domain.repository

import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail

interface UserRepository {
    suspend fun getUsers() : List<User>
    suspend fun getUserDetail(userName: String) : UserDetail
    suspend fun getUserRepos(userName: String) : List<Repo>
}