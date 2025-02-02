package com.example.githubclient.ui.userdetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailAdditionalInfo(
    location: String?,
    company: String?,
    blog: String?,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        UserInfoItem(icon = Icons.Filled.LocationOn, text = location ?: "Not Available")
        UserInfoItem(icon = Icons.Filled.Link, text = blog ?: "github.blog")
        UserInfoItem(icon = Icons.Filled.Business, text = company ?: "Not Available")
    }
}

@Composable
fun UserInfoItem(icon: ImageVector, text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}