package com.example.githubclient.data.remote

import com.example.githubclient.data.model.RepoDto
import com.example.githubclient.data.model.UserDetailDto
import com.example.githubclient.data.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("since") startId: Int,
        @Query("per_page") perPage: Int
    ) : List<UserDto>

    @GET("users/{userName}")
    suspend fun getUserDetail(@Path("userName") userName: String) : UserDetailDto

    @GET("users/{userName}/repos")
    suspend fun getUserRepos(@Path("userName") userName: String) : List<RepoDto>
}