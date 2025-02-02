package com.example.githubclient.domain.model

// Represents a GitHub user
data class User (
    val id: Int,
    val userName: String,
    val avatarUrl: String,
    val siteAdmin: Boolean,
    val type: String,
)