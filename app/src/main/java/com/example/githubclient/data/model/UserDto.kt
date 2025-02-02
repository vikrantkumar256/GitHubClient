package com.example.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("id") val id: Int,
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("site_admin") val siteAdmin: Boolean,
    @SerializedName("type") val type: String,
)