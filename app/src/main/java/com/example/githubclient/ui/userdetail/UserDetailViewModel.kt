package com.example.githubclient.ui.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.domain.repository.UserRepository
import com.example.githubclient.ui.userdetail.state.UserDetailState
import com.example.githubclient.ui.userdetail.state.UserReposState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* ViewModel for managing user details and repositories
* Uses Hilt for dependency injection
* */
@HiltViewModel
class UserDetailViewModel @Inject constructor( private val userRepository: UserRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    //Extract the userName from savedStateHandle
    private val userName: String = savedStateHandle["userName"] ?: ""

    // StateFlow to manage user detail UI state
    private val _uiDetailState = MutableStateFlow<UserDetailState>(UserDetailState.Loading)
    val uiDetailState : StateFlow<UserDetailState> = _uiDetailState.asStateFlow()

    // StateFlow to manage user repositories UI state
    private val _uiReposState = MutableStateFlow<UserReposState>(UserReposState.Loading)
    val uiReposState : StateFlow<UserReposState> = _uiReposState.asStateFlow()

    init {
        // Initialize API calls when ViewModel is created
        fetchUserDetail()
        fetchUserRepos()
    }

    // Fetch user details and update state
    fun fetchUserDetail(){
        viewModelScope.launch {
            _uiDetailState.value = UserDetailState.Loading
            try {
                val userDetail = userRepository.getUserDetail(userName)
                _uiDetailState.value = UserDetailState.Success(userDetail)
            }
            catch (e: Exception)
            {
                _uiDetailState.value = UserDetailState.Error(message = e.message ?: "Unknown Error Occurred")
            }
        }
    }

    // Fetch user repositories and update state
    fun fetchUserRepos(){
        viewModelScope.launch {
            _uiReposState.value = UserReposState.Loading
            try{
                val userRepos = userRepository.getUserRepos(userName)
                _uiReposState.value = UserReposState.Success(userRepos)
            }
            catch(e: Exception)
            {
                _uiReposState.value = UserReposState.Error(message = e.message ?: "Unexpected Error Occured")
            }
        }
    }
}
