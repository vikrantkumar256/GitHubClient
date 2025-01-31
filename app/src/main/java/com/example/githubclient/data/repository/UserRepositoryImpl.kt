package com.example.githubclient.data.repository

import com.example.githubclient.data.mapper.UserMapper
import com.example.githubclient.data.remote.GitHubApiService
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: GitHubApiService, private val mapper: UserMapper) : UserRepository {
    override suspend fun getUsers(): List<User> {
        val usersDto = api.getUsers()
        return usersDto.map { mapper.toDomain(it) }
    }
}