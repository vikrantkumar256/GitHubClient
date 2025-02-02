package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.UserDto
import com.example.githubclient.domain.model.User
import javax.inject.Inject

/**
* Map a UserDto Object to User domain model Object
*/
class UserMapper @Inject constructor() {
    fun toDomain(dto: UserDto): User {
        return User(
            id = dto.id,
            userName = dto.userName,
            avatarUrl = dto.avatarUrl,
            siteAdmin = dto.siteAdmin,
            type = dto.type
        )
    }
}