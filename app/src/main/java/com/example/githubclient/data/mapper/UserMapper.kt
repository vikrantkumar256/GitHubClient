package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.UserDto
import com.example.githubclient.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun toDomain(dto: UserDto): User {
        return User(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
        )
    }
}