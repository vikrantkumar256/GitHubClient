package com.example.githubclient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for initializing Dagger Hilt dependency injection.
 */
@HiltAndroidApp
class CoreApplication : Application()