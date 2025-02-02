package com.example.githubclient.ui.userdetail

import androidx.lifecycle.SavedStateHandle
import com.example.githubclient.domain.model.Repo
import com.example.githubclient.domain.model.UserDetail
import com.example.githubclient.domain.repository.UserRepository
import com.example.githubclient.ui.userdetail.state.UserDetailState
import com.example.githubclient.ui.userdetail.state.UserReposState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.rules.TestWatcher
import org.junit.Rule
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class UserDetailViewModelTest {

    // Rule to control coroutines behavior during tests
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var viewModel: UserDetailViewModel
    private val mockRepository: UserRepository = mockk()
    private val savedStateHandle = SavedStateHandle()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the main dispatcher to test dispatcher for coroutine-based tests
        Dispatchers.setMain(testDispatcher)
        savedStateHandle["userName"] = "user1"  // Mocked user name for the test
        viewModel = UserDetailViewModel(mockRepository, savedStateHandle)  // Initialize the ViewModel
    }

    @After
    fun tearDown() {
        // Reset the dispatcher after each test
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchUserDetail should update state to Success when data is fetched`() = runTest {
        // Mock data for user details
        val mockUserDetail = UserDetail(
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
        // Mock the repository method to return mock user detail
        coEvery { mockRepository.getUserDetail("user1") } returns mockUserDetail

        // Call the method to fetch user detail
        viewModel.fetchUserDetail()
        advanceUntilIdle()  // Ensure all coroutines are completed

        // Assert that the state is updated to Success with the correct user detail
        assertTrue(viewModel.uiDetailState.value is UserDetailState.Success)
        val successState = viewModel.uiDetailState.value as UserDetailState.Success
        assertEquals(mockUserDetail, successState.userDetail)

        // Verify that the repository method was called
        coVerify { mockRepository.getUserDetail("user1") }
    }

    @Test
    fun `fetchUserDetail should handle errors properly`() = runTest {
        val errorMessage = "Network error"
        // Mock the repository to throw an exception
        coEvery { mockRepository.getUserDetail("user1") } throws Exception(errorMessage)

        // Call the method to fetch user detail
        viewModel.fetchUserDetail()
        advanceUntilIdle()  // Ensure all coroutines are completed

        // Assert that the state is updated to Error with the correct error message
        assertTrue(viewModel.uiDetailState.value is UserDetailState.Error)
        val errorState = viewModel.uiDetailState.value as UserDetailState.Error
        assertEquals(errorMessage, errorState.message)
    }

    @Test
    fun `fetchUserRepos should update state to Success when data is fetched`() = runTest {
        // Mock data for user repositories
        val mockRepos = listOf(
            Repo(
                name = "Repo1",
                description = "Description1",
                url = "https://github.com/mojombo/repo1",
                language = "Kotlin",
                stars = 50,
                forks = 30
            ),
            Repo(
                name = "Repo2",
                description = "Description2",
                url = "https://github.com/mojombo/repo2",
                language = "Java",
                stars = 100,
                forks = 40
            )
        )
        // Mock the repository method to return mock repositories
        coEvery { mockRepository.getUserRepos("user1") } returns mockRepos

        // Call the method to fetch user repositories
        viewModel.fetchUserRepos()
        advanceUntilIdle()  // Ensure all coroutines are completed

        // Assert that the state is updated to Success with the correct repositories
        assertTrue(viewModel.uiReposState.value is UserReposState.Success)
        val successState = viewModel.uiReposState.value as UserReposState.Success
        assertEquals(mockRepos, successState.repos)

        // Verify that the repository method was called
        coVerify { mockRepository.getUserRepos("user1") }
    }

    @Test
    fun `fetchUserRepos should handle errors properly`() = runTest {
        val errorMessage = "Unexpected error"
        // Mock the repository to throw an exception
        coEvery { mockRepository.getUserRepos("user1") } throws Exception(errorMessage)

        // Call the method to fetch user repositories
        viewModel.fetchUserRepos()
        advanceUntilIdle()  // Ensure all coroutines are completed

        // Assert that the state is updated to Error with the correct error message
        assertTrue(viewModel.uiReposState.value is UserReposState.Error)
        val errorState = viewModel.uiReposState.value as UserReposState.Error
        assertEquals(errorMessage, errorState.message)
    }
}

// CoroutineTestRule to set and reset the main dispatcher for coroutine tests
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {
    private val testDispatcher = StandardTestDispatcher()

    // Set the dispatcher to the test dispatcher before each test
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    // Reset the dispatcher after each test
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
