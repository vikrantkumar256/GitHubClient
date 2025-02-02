package com.example.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailDto(
    @SerializedName("login") val userName: String,
    @SerializedName("name") val name: String?,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("company") val company: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("public_gists") val publicGists: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("site_admin") val siteAdmin: Boolean,
    @SerializedName("type") val type: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("blog") val blog: String,
)