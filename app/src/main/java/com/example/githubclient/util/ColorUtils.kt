package com.example.githubclient.util

import androidx.compose.ui.graphics.Color

/**
 * Returns the corresponding color for a given programming language.
 */
fun getLanguageColor(language: String): Color {
    return when (language) {
        "CSS" -> Color(0xFF563D7C)
        "JavaScript" -> Color(0xFFF1E05A)
        "Kotlin" -> Color(0xFF7F52FF)
        "Java" -> Color(0xFFB07219)
        else -> Color.Gray
    }
}