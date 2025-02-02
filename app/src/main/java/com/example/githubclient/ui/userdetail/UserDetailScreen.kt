package com.example.githubclient.ui.userdetail

import UserRepositorySection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubclient.ui.common.ErrorScreen
import com.example.githubclient.ui.common.LoadingScreen
import com.example.githubclient.ui.userdetail.component.UserDetailAdditionalInfo
import com.example.githubclient.ui.userdetail.component.UserDetailBio
import com.example.githubclient.ui.userdetail.component.UserDetailHeader
import com.example.githubclient.ui.userdetail.component.UserDetailStats
import com.example.githubclient.ui.userdetail.state.UserDetailState
import com.example.githubclient.ui.userdetail.state.UserReposState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel = hiltViewModel(), userName: String, onBack: () -> Unit) {
    val uiDetailState by viewModel.uiDetailState.collectAsState()
    val uiReposState by viewModel.uiReposState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(userName) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = Color(0xFFE8EAF6)
    ) {
        innerPadding ->
        Box(modifier =
        Modifier.padding(innerPadding)
        ) {
            when (uiDetailState) {
                is UserDetailState.Loading -> LoadingScreen()
                is UserDetailState.Success -> {
                    val userDetail = (uiDetailState as UserDetailState.Success).userDetail

                    Card (
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(12.dp),
                        colors = CardDefaults.cardColors(Color(0xFFFFFFFF))
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(16.dp, 24.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            // User Detail Header
                            UserDetailHeader(
                                avatarUrl = userDetail.avatarUrl,
                                name = userDetail.name,
                                userName = userDetail.userName,
                                createdAt = userDetail.createdAt,
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // User Detail Bio
                            UserDetailBio(bio = userDetail.bio)

                            // User Detail Stats
                            UserDetailStats(
                                publicRepos = userDetail.publicRepos,
                                followers = userDetail.followers,
                                following = userDetail.following
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // User Detail Additional Info
                            UserDetailAdditionalInfo(
                                location = userDetail.location,
                                company = userDetail.company,
                                blog = userDetail.blog
                            )

                            HorizontalDivider()

                            if(uiReposState is UserReposState.Success) {
                                val userRepos = (uiReposState as UserReposState.Success).repos
                                UserRepositorySection(repos = userRepos)
                            }
                        }
                    }
                }
                is UserDetailState.Error -> {
                    ErrorScreen(message = (uiDetailState as UserDetailState.Error).message) {
                        viewModel.fetchUserDetail()
                        viewModel.fetchUserRepos()
                    }
                }
            }
        }
    }
}
