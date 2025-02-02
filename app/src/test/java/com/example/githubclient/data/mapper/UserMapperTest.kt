package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.UserDto
import com.example.githubclient.domain.model.User
import org.junit.Test
import org.junit.Assert.*

class UserMapperTest {

    private val userMapper = UserMapper()

    @Test
    fun `test toDomain maps UserDto to User correctly`() {
        // Creating a UserDto for the test
        val userDto = UserDto(
            id = 1,
            userName = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            siteAdmin = false,
            type = "User"
        )

        // Expected result after mapping
        val expectedUser = User(
            id = 1,
            userName = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            siteAdmin = false,
            type = "User"
        )

        // Mapping the DTO to domain model
        val result = userMapper.toDomain(userDto)

        // Assert that the result matches the expected User
        assertEquals(expectedUser, result)
    }
}
