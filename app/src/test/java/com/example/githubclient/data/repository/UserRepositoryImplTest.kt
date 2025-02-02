package com.example.githubclient.data.repository

import com.example.githubclient.data.mapper.RepoMapper
import com.example.githubclient.data.mapper.UserDetailMapper
import com.example.githubclient.data.mapper.UserMapper
import com.example.githubclient.data.model.RepoDto
import com.example.githubclient.data.model.UserDetailDto
import com.example.githubclient.data.model.UserDto
import com.example.githubclient.data.remote.GitHubApiService
import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private val api: GitHubApiService = mockk()
    private val userMapper: UserMapper = mockk()
    private val userDetailMapper: UserDetailMapper = mockk()
    private val repoMapper: RepoMapper = mockk()

    // Set up mock dependencies and repository instance before each test
    @Before
    fun setUp() {
        repository = UserRepositoryImpl(api, userMapper, userDetailMapper, repoMapper)
    }

    // Test case for fetching and mapping users
    @Test
    fun `getUsers should return mapped list of users`() = runBlocking {
        // Prepare mock API response with a list of UserDto objects
        val userDtoList = listOf(
            UserDto(
                id = 1,
                userName = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                siteAdmin = false,
                type = "User"
            )
        )

        // Expected domain model list
        val userList = listOf(
            User(
                id = 1,
                userName = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                siteAdmin = false,
                type = "User"
            )
        )

        // Mock API and mapper behaviors
        coEvery { api.getUsers(0, 10) } returns userDtoList
        coEvery { userMapper.toDomain(userDtoList[0]) } returns userList[0]

        // Call the repository and verify the result
        val result = repository.getUsers(0, 10)

        assertEquals(1, result.size)
        assertEquals(userList[0], result[0])
    }

    // Test case for fetching and mapping user details
    @Test
    fun `getUserDetail should return mapped user detail`() = runBlocking {
        // Prepare mock API response with user details
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

        // Expected mapped user detail
        val userDetail = UserDetail(
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
            blog = "test.github.com",
            createdAt = "2025-02-02T15:30:00+00:00[UTC]",
            updatedAt = "2025-02-02T15:30:00+00:00[UTC]",
            siteAdmin = false,
            type = "User"
        )

        // Mock API and mapper behaviors
        coEvery { api.getUserDetail("mojombo") } returns userDetailDto
        coEvery { userDetailMapper.toDomain(userDetailDto) } returns userDetail

        // Call the repository and verify the result
        val result = repository.getUserDetail("mojombo")

        assertEquals(userDetail, result)
    }

    // Test case for fetching and sorting user repositories
    @Test
    fun `getUserRepos should return sorted list of repos`() = runBlocking {
        // Prepare mock API response with a list of RepoDto objects
        val repoDtoList = listOf(
            RepoDto(
                name = "Repo1",
                description = "Description1",
                url = "https://github.com/mojombo/repo1",
                language = "Kotlin",
                stars = 50,
                forks = 30
            ),
            RepoDto(
                name = "Repo2",
                description = "Description2",
                url = "https://github.com/mojombo/repo2",
                language = "Java",
                stars = 100,
                forks = 40
            )
        )

        // Expected sorted list of repos
        val repoList = listOf(
            Repo(name = "Repo1", description = "Description1", url = "https://github.com/mojombo/repo1", language = "Kotlin", stars = 50, forks = 30),
            Repo(name = "Repo2", description = "Description2", url = "https://github.com/mojombo/repo2", language = "Java", stars = 100, forks = 40)
        )

        // Mock API and mapper behaviors
        coEvery { api.getUserRepos("mojombo") } returns repoDtoList
        coEvery { repoMapper.toDomain(repoDtoList[0]) } returns repoList[0]
        coEvery { repoMapper.toDomain(repoDtoList[1]) } returns repoList[1]

        // Call the repository and verify the result (sorted by stars)
        val result = repository.getUserRepos("mojombo")
        assertEquals("Repo2", result[0].name)
        assertEquals("Repo1", result[1].name)
    }
}
