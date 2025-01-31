package com.example.githubclient.domain.repository

import com.example.githubclient.domain.model.User

interface UserRepository {
    suspend fun getUsers() : List<User>
}