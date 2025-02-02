package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.UserDetailDto
import com.example.githubclient.domain.model.UserDetail
import javax.inject.Inject

class UserDetailMapper @Inject constructor() {
    fun toDomain(dto: UserDetailDto): UserDetail {
        return UserDetail(
            userName = dto.userName,
            avatarUrl = dto.avatarUrl,
            name = dto.name,
            company = dto.company,
            location = dto.location,
            bio = dto.bio,
            publicRepos = dto.publicRepos,
            publicGists = dto.publicGists,
            followers = dto.followers,
            following = dto.following,
            updatedAt = dto.updatedAt,
            siteAdmin = dto.siteAdmin,
            type = dto.type,
            createdAt = dto.createdAt,
            blog = dto.blog
        )
    }
}