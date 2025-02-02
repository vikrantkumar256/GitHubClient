package com.example.githubclient.ui.userlist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githubclient.domain.model.User

@Composable
fun UserListItem(user: User, onClick: (String) -> Unit, isLastItem: Boolean, onLoadMoreClick: () -> Unit, showLoadMore: Boolean) {
    Card(
        onClick = { onClick(user.userName) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(user.userName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (user.siteAdmin) {
                        Badge(
                            modifier = Modifier.padding(end = 8.dp),
                            content = { Text(text = "STAFF", style = MaterialTheme.typography.labelMedium) }
                        )
                        Text("GitHub Team", color = MaterialTheme.colorScheme.outline)
                    } else {
                        Text(user.type, color = MaterialTheme.colorScheme.outline)
                    }
                }
            }
        }
    }
    if(isLastItem && showLoadMore)
    {
        Button(onClick = onLoadMoreClick, modifier = Modifier.padding(8.dp)) {
            Text(text = "Load More")
        }
    }
}
