package com.example.githubclient.domain.model

data class User (
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val siteAdmin: Boolean,
    val type: String,
)