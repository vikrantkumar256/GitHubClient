package com.example.githubclient.domain.model

// Represents a GitHub repository
data class Repo(
    val name: String,
    val description: String?,
    val url: String,
    val language: String?,
    val stars: Int,
    val forks: Int
)