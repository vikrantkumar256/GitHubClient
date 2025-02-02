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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githubclient.domain.model.User

/*
 * UserListItem Composable:
 * Displays a single user's details- avatar, username, and user type or staff badge.
 * Includes a "Load More" button when it's the last item in the list and more items are available.
 * Handles user item clicks and "Load More" button interactions.
 */
@Composable
fun UserListItem(user: User, onClick: (String) -> Unit, isLastItem: Boolean, onLoadMoreClick: () -> Unit, showLoadMore: Boolean) {

    // Card representing the individual user item, with rounded corners and elevation
    Card(
        onClick = { onClick(user.userName) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // AsyncImage to load user's avatar in a circular shape with a border
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

                // Row layout to display either staff badge or user type
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

    // If it's the last item but showLoadMore is true, show the "Load More" button
    if(isLastItem && showLoadMore)
    {
        Button(onClick = onLoadMoreClick, modifier = Modifier.padding(8.dp)) {
            Text(text = "Load More")
        }
    }
}
