package com.example.githubclient.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.domain.repository.UserRepository
import com.example.githubclient.ui.userlist.state.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserListViewModel @Inject constructor( private val userRepository: UserRepository) : ViewModel() {
    private val startId = 0 //starting index for fetching github user
    private val usersDisplayedPerPage = 10 // Number of users to display per page
    val usersFetchLimit = 100 // Maximum limit for fetching users to fetch

    // StateFlow to manage users list state
    private val _uiState = MutableStateFlow<UserListState>(UserListState.Loading)
    val uiState : StateFlow<UserListState> = _uiState.asStateFlow()

    // StateFlow to manage the total number of users displayed
    private val _totalUserDisplayed = MutableStateFlow(usersDisplayedPerPage)
    val totalUserDisplayed: StateFlow<Int> = _totalUserDisplayed.asStateFlow()

    init {
        // Load users when the ViewModel is initialized
        loadUsers()
    }

    // function to load intial list of users
    fun loadUsers(){
        viewModelScope.launch {
            _uiState.value = UserListState.Loading
            try {
                val users = userRepository.getUsers(startId = startId,usersFetchLimit).toMutableList()
                _uiState.value = UserListState.Success(users)
            }
            catch (e: Exception)
            {
                // Handle any errors and update the state with an error message
                _uiState.value = UserListState.Error(message = e.message ?: "Unknown error occurred")
            }
        }
    }

    fun loadMoreUsers(){
        // Increase the number of users displayed
        _totalUserDisplayed.value += usersDisplayedPerPage;
    }
}