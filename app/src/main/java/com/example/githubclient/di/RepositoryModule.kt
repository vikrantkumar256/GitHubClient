package com.example.githubclient.di

import com.example.githubclient.data.repository.UserRepositoryImpl
import com.example.githubclient.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module for binding repository implementations to their interfaces.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    // Bind UserRepositoryImpl to UserRepository interface.
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl) : UserRepository
}