package com.example.githubclient.domain.model

data class UserDetail(
    val userName: String,
    val name: String?,
    val avatarUrl: String,
    val company: String?,
    val location: String?,
    val bio: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val updatedAt: String,
    val createdAt: String,
    val siteAdmin: Boolean,
    val blog: String?,
    val type: String
)