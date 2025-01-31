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

    private val _uiState = MutableStateFlow<UserListState>(UserListState.Loading)
    val uiState : StateFlow<UserListState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers(){
        viewModelScope.launch {
            _uiState.value = UserListState.Loading
            try {
                val result = userRepository.getUsers()
                _uiState.value = UserListState.Success(result);
            }
            catch (e: Exception)
            {
                _uiState.value = UserListState.Error(message = e.message ?: "Unknown error occured")
            }
        }
    }
}