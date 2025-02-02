package com.example.githubclient.data.repository

import com.example.githubclient.data.mapper.RepoMapper
import com.example.githubclient.data.mapper.UserDetailMapper
import com.example.githubclient.data.mapper.UserMapper
import com.example.githubclient.data.model.UserDto
import com.example.githubclient.data.remote.GitHubApiService
import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail
import com.example.githubclient.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: GitHubApiService,
    private val userMapper: UserMapper,
    private val userDetailMapper: UserDetailMapper,
    private val repoMapper: RepoMapper
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        val userDtos = api.getUsers()
        return userDtos.map { userMapper.toDomain(it) }
    }

    override suspend fun getUserDetail(userName: String): UserDetail {
        val userDetailDto = api.getUserDetail(userName = userName)
        return userDetailMapper.toDomain(userDetailDto)
    }

    override suspend fun getUserRepos(userName: String): List<Repo> {
        val repoDtos = api.getUserRepos(userName = userName)
        val repos = repoDtos.map { repoMapper.toDomain(it) }
        // sort the domain model based on (fork + stars)
        val sortedRepos = repos.sortedByDescending { it.forks + it.stars }
        return sortedRepos
    }
}