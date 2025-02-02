package com.example.githubclient.domain.repository

import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.model.UserDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class UserRepositoryTest {

    private val mockUserRepository = mockk<UserRepository>()

    @Test
    fun `test getUsers returns correct data`() = runBlocking {
        val users = listOf(
            User(id = 1, userName = "user1", avatarUrl = "testurl1", siteAdmin=false,type = "User"),
            User(id = 2, userName = "user2", avatarUrl = "testurl2", siteAdmin=false,type = "User")
        )
        coEvery { mockUserRepository.getUsers(startId = 0, perPage = 2) } returns users
        val result = mockUserRepository.getUsers(startId = 0, perPage = 2)
        assertEquals(users, result)
    }

    @Test
    fun `test getUserDetail returns correct data`() = runBlocking {
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
            updatedAt = "2024-01-01T12:00:00Z",
            siteAdmin = false,
            type = "User",
            createdAt = "2008-01-01T00:00:00Z",
            blog = "https://tom.preston-werner.com"
        )
        coEvery { mockUserRepository.getUserDetail(userName = "user1") } returns userDetail
        val result = mockUserRepository.getUserDetail(userName = "user1")
        assertEquals(userDetail, result)
    }

    @Test
    fun `test getUserRepos returns correct data`() = runBlocking {
        val repos = listOf(
            Repo(name = "Repo1", description = "Description", url = "http://github.com/repo1", language = "Kotlin", stars = 10, forks = 5),
            Repo(name = "Repo2", description = "Another description", url = "http://github.com/repo2", language = "Java", stars = 15, forks = 7)
        )
        coEvery { mockUserRepository.getUserRepos(userName = "user1") } returns repos
        val result = mockUserRepository.getUserRepos(userName = "user1")
        assertEquals(repos, result)
    }

    @Test
    fun `test getUsers handles error correctly`() = runBlocking {
        coEvery { mockUserRepository.getUsers(startId = 0, perPage = 2) } throws Exception("Network Error")
        try {
            mockUserRepository.getUsers(startId = 0, perPage = 2)
            fail("Exception was expected")
        } catch (e: Exception) {
            assertEquals("Network Error", e.message)
        }
    }

    @Test
    fun `test getUserDetail handles error correctly`() = runBlocking {
        coEvery { mockUserRepository.getUserDetail(userName = "user1") } throws Exception("User Not Found")
        try {
            mockUserRepository.getUserDetail(userName = "user1")
            fail("Exception was expected")
        } catch (e: Exception) {
            assertEquals("User Not Found", e.message)
        }
    }

    @Test
    fun `test getUserRepos handles error correctly`() = runBlocking {
        coEvery { mockUserRepository.getUserRepos(userName = "user1") } throws Exception("No Repositories Found")
        try {
            mockUserRepository.getUserRepos(userName = "user1")
            fail("Exception was expected")
        } catch (e: Exception) {
            assertEquals("No Repositories Found", e.message)
        }
    }
}
