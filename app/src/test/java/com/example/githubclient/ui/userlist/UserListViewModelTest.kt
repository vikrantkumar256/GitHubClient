package com.example.githubclient.ui.userlist

import com.example.githubclient.domain.model.User
import com.example.githubclient.domain.repository.UserRepository
import com.example.githubclient.ui.userlist.state.UserListState
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
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    // Rule to control coroutines behavior during tests
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var viewModel: UserListViewModel
    private val mockRepository: UserRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)  // Set the test dispatcher
        viewModel = UserListViewModel(mockRepository)  // Initialize the ViewModel
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Reset the dispatcher after tests
    }

    @Test
    fun `initial load should show loading then success state`() = runTest {
        // Given: Mocking the repository response
        val mockUsers = listOf(
            User(id = 1, userName = "user1", avatarUrl = "testurl1", siteAdmin = false, type = "User"),
            User(id = 2, userName = "user2", avatarUrl = "testurl2", siteAdmin = false, type = "User")
        )

        coEvery {
            mockRepository.getUsers(
                startId = 0,
                perPage = 100
            )
        } returns mockUsers

        // When: Trigger the user loading
        viewModel.loadUsers()

        // Then: Collect and assert states
        advanceUntilIdle() // Ensure all coroutine tasks are completed

        assertTrue(viewModel.uiState.value is UserListState.Success)  // State should be Success
        val successState = viewModel.uiState.value as UserListState.Success
        assertEquals(mockUsers, successState.users)

        coVerify {  // Verify that the repository's `getUsers` was called with expected params
            mockRepository.getUsers(
                startId = 0,
                perPage = 100
            )
        }
    }

    @Test
    fun `loadUsers should handle errors properly`() = runTest {
        // Given: Mocking an error from repository
        val errorMessage = "Network error"
        coEvery {
            mockRepository.getUsers(any(), any())
        } throws Exception(errorMessage)

        // When: Call the loadUsers function
        viewModel.loadUsers()

        // Then: Assert the state transition to Error
        advanceUntilIdle() // Ensure coroutine completes

        assertTrue(viewModel.uiState.value is UserListState.Error)  // State should be Error
        val errorState = viewModel.uiState.value as UserListState.Error
        assertEquals(errorMessage, errorState.message)
    }

    @Test
    fun `loadMoreUsers should increment displayed count correctly`() {
        // Initial state
        assertEquals(10, viewModel.totalUserDisplayed.value)

        // First increment
        viewModel.loadMoreUsers()
        assertEquals(20, viewModel.totalUserDisplayed.value)

        // Second increment
        viewModel.loadMoreUsers()
        assertEquals(30, viewModel.totalUserDisplayed.value)
    }

    @Test
    fun `empty user list should show empty state`() = runTest {
        // Given: Mocking empty list of users
        coEvery { mockRepository.getUsers(any(), any()) } returns emptyList()

        // When: Call the loadUsers function
        viewModel.loadUsers()

        // Then: Ensure that the UI state is Success and empty list
        advanceUntilIdle() // Ensure coroutine completes

        assertTrue(viewModel.uiState.value is UserListState.Success)
        val successState = viewModel.uiState.value as UserListState.Success
        assertTrue(successState.users.isEmpty())
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {
    private val testDispatcher = StandardTestDispatcher()

    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)  // Set the dispatcher to the test one
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()  // Reset the dispatcher after the test
    }
}
