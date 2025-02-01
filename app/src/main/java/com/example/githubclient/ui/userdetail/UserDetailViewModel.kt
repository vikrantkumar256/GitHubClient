package com.example.githubclient.ui.userdetail

import androidx.lifecycle.ViewModel
import com.example.githubclient.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor( private val userRepository: UserRepository) : ViewModel() {

}
