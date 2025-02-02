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
    private val startId = 0
    private val usersDisplayedPerPage = 10
    val usersFetchLimit = 100

    // StateFlow to manage users list state
    private val _uiState = MutableStateFlow<UserListState>(UserListState.Loading)
    val uiState : StateFlow<UserListState> = _uiState.asStateFlow()

    private val _totalUserDisplayed = MutableStateFlow(usersDisplayedPerPage)
    val totalUserDisplayed: StateFlow<Int> = _totalUserDisplayed.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers(){
        viewModelScope.launch {
            _uiState.value = UserListState.Loading
            try {
                val users = userRepository.getUsers(startId = startId,usersFetchLimit).toMutableList()
                _uiState.value = UserListState.Success(users)
            }
            catch (e: Exception)
            {
                _uiState.value = UserListState.Error(message = e.message ?: "Unknown error occurred")
            }
        }
    }

    fun loadMoreUsers(){
        _totalUserDisplayed.value += usersDisplayedPerPage;
    }
}