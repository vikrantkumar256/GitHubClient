package com.example.githubclient.ui.userlist.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githubclient.domain.model.User

@Composable
fun UserListItem(user: User) {
    Card(
        onClick = { /* Navigate to detail */ },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(Modifier.padding(16.dp)) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp)) // Improved spacing

            Column(Modifier.weight(1f)) {
                Text(user.login, style = MaterialTheme.typography.titleMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (user.siteAdmin) {
                        Badge(
                            modifier = Modifier.padding(end = 8.dp),
                            content = { Text(text = "STAFF", style = MaterialTheme.typography.labelMedium) }
                        )
                    }
                    Text(user.type, color = MaterialTheme.colorScheme.outline)
                }
            }
        }
    }
}
