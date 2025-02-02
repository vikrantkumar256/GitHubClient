package com.example.githubclient.ui.userdetail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Displays the user's bio in a box. If the bio is not empty, it shows the bio text with a limit of 3 lines,
 * truncating with an ellipsis if it's too long. If the bio is empty or null, it displays a default message.
 */
@Composable
fun UserDetailBio(bio: String?) {
    Box(modifier = Modifier.padding(0.dp, 32.dp)) {
        if (!bio.isNullOrEmpty()) {
            Text(
                text = bio,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            Text(
                text = "This profile has no bio",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
