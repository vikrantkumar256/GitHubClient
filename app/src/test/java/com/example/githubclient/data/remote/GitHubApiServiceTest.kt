package com.example.githubclient.data.remote

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: GitHubApiService

    // Set up the MockWebServer and API service before each test
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Mock API URL for testing
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }

    // Shutdown the MockWebServer after each test
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // Test case for fetching users
    @Test
    fun `getUsers should return list of users`() = runBlocking {
        // Prepare a mock response with sample user data
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                [
                    {
                        "id": 1,
                        "login": "mojombo",
                        "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4",
                        "site_admin": false,
                        "type": "User"
                    }
                ]
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)  // Enqueue the mock response

        // Call API service to fetch users
        val users = apiService.getUsers(startId = 0, perPage = 10)

        // Assert that the response is as expected
        assertEquals(1, users.size)
        assertEquals(1, users[0].id)
        assertEquals("mojombo", users[0].userName)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", users[0].avatarUrl)
        assertEquals(false, users[0].siteAdmin)
        assertEquals("User", users[0].type)
    }

    // Test case for fetching user details
    @Test
    fun `getUserDetail should return user details`() = runBlocking {
        // Prepare a mock response with sample user detail data
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                {
                    "id": 1,
                    "login": "mojombo",
                    "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4",
                    "site_admin": false,
                    "type": "User"
                }
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)  // Enqueue the mock response

        // Call API service to fetch user details
        val userDetail = apiService.getUserDetail("mojombo")

        // Assert that the user detail response is as expected
        assertEquals("mojombo", userDetail.userName)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", userDetail.avatarUrl)
        assertEquals(false, userDetail.siteAdmin)
        assertEquals("User", userDetail.type)
    }

    // Test case for fetching user repositories
    @Test
    fun `getUserRepos should return list of repos`() = runBlocking {
        // Prepare a mock response with an empty list of repositories
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[]")

        mockWebServer.enqueue(mockResponse)  // Enqueue the mock response

        // Call API service to fetch user repositories
        val repos = apiService.getUserRepos("mojombo")

        // Assert that the repositories list is empty
        assertEquals(0, repos.size)
    }
}
