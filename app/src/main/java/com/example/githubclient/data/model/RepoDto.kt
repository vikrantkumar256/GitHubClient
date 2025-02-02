package com.example.githubclient.data.model

import com.google.gson.annotations.SerializedName

/**
* Data Transfer Object for a GitHub repository,
* used for serializing/deserializing JSON data.
*/
data class RepoDto(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("html_url") val url: String,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks") val forks: Int
)