package com.example.githubclient.data.repository

import com.example.githubclient.data.mapper.RepoMapper
import com.example.githubclient.data.mapper.UserDetailMapper
import com.example.githubclient.data.mapper.UserMapper
import com.example.githubclient.data.remote.GitHubApiService
import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail
import com.example.githubclient.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Implementation of the UserRepository interface that interacts with the GitHub API.
 */
class UserRepositoryImpl @Inject constructor(
    private val api: GitHubApiService,
    private val userMapper: UserMapper,
    private val userDetailMapper: UserDetailMapper,
    private val repoMapper: RepoMapper
) : UserRepository {

    // Get a list of users.
    override suspend fun getUsers(startId: Int, perPage: Int): List<User> {
        val userDtos = api.getUsers(startId, perPage)
        return userDtos.map { userMapper.toDomain(it) }
    }

    // Get detailed information of a specific user.
    override suspend fun getUserDetail(userName: String): UserDetail {
        val userDetailDto = api.getUserDetail(userName = userName)
        return userDetailMapper.toDomain(userDetailDto)
    }

    // Get and sort repositories of a specific user by stars and forks.
    override suspend fun getUserRepos(userName: String): List<Repo> {
        val repoDtos = api.getUserRepos(userName = userName)
        val repos = repoDtos.map { repoMapper.toDomain(it) }
        val sortedRepos = repos.sortedByDescending { it.forks + it.stars }
        return sortedRepos
    }
}