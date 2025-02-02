package com.example.githubclient.ui.userdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Displays user statistics such as public repositories, followers, and following in a card layout.
 * Each stat is shown with a label and corresponding value in a horizontal row.
 */
@Composable
fun UserDetailStats(
    publicRepos: Int,
    followers: Int,
    following: Int
) {
    Card(
        colors = CardDefaults.cardColors(Color(0xFFE8EAF6)),
        shape = RoundedCornerShape(36.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Display stats for Repos, Followers, and Following
            StatItem(label = "Repos", value = publicRepos)
            StatItem(label = "Followers", value = followers)
            StatItem(label = "Following", value = following)
        }
    }
}

/**
 * Displays a single statistic item with a label and its corresponding value.
 */
@Composable
fun StatItem(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
