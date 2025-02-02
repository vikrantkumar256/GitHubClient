package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.UserDetailDto
import com.example.githubclient.domain.model.UserDetail
import org.junit.Test
import org.junit.Assert.*

class UserDetailMapperTest {

    private val userDetailMapper = UserDetailMapper()

    @Test
    fun `test toDomain maps UserDetailDto to UserDetail correctly`() {
        // Creating a UserDetailDto for the test
        val userDetailDto = UserDetailDto(
            userName = "mojombo",
            name = "Tom Preston-Werner",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            company = "GitHub",
            location = "San Francisco",
            bio = "Co-founder of GitHub",
            publicRepos = 20,
            publicGists = 5,
            followers = 100,
            following = 50,
            updatedAt = "2024-01-01T12:00:00Z",
            siteAdmin = false,
            type = "User",
            createdAt = "2008-01-01T00:00:00Z",
            blog = "https://tom.preston-werner.com"
        )

        // Expected result after mapping
        val expectedUserDetail = UserDetail(
            userName = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            name = "Tom Preston-Werner",
            company = "GitHub",
            location = "San Francisco",
            bio = "Co-founder of GitHub",
            publicRepos = 20,
            publicGists = 5,
            followers = 100,
            following = 50,
            updatedAt = "2024-01-01T12:00:00Z",
            siteAdmin = false,
            type = "User",
            createdAt = "2008-01-01T00:00:00Z",
            blog = "https://tom.preston-werner.com"
        )

        // Mapping the DTO to domain model
        val result = userDetailMapper.toDomain(userDetailDto)

        // Assert that the result matches the expected UserDetail
        assertEquals(expectedUserDetail, result)
    }
}
