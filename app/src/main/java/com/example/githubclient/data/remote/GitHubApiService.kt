package com.example.githubclient.data.remote

import com.example.githubclient.data.model.UserDto
import retrofit2.http.GET

interface GitHubApiService {

    @GET("/users")
    suspend fun getUsers() : List<UserDto>
}