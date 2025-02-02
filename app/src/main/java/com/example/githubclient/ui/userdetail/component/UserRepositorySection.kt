import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Source
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.example.githubclient.R
import com.example.githubclient.domain.model.Repo
import com.example.githubclient.util.getLanguageColor

/**
 * Displays the list of top repositories with details such as the repository name, description, language, stars, and forks.
 * Each repository item is clickable and opens the repository URL in a browser.
 */
@Composable
fun UserRepositorySection(repos: List<Repo>) {
    Text(
        text = "Top Repository",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
    )

    Column {
        repos.take(4).forEach { repo ->
            RepositoryItem(repo)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

/**
 * Displays an individual repository item with its name, description, language, stars, and forks.
 * The repository name is clickable and opens the repository in a browser.
 */
@Composable
fun RepositoryItem(repo: Repo) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Color(0xFFE8EAF6)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.url))
                context.startActivity(intent)
            }
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Row: Repo Icon + Name + Public Tag
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Source,
                    contentDescription = "Repo Icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = repo.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.url))
                        context.startActivity(intent)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Public",
                    fontSize = 12.sp,
                    color = Color(0xFF1A237E),
                    modifier = Modifier
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            // Description
            Text(
                text = repo.description ?: "No description available",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Row: Language, Stars, Forks
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                repo.language?.let {
                    language ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(getLanguageColor(language), shape = RoundedCornerShape(50))
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = language, fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Stars
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Stars",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = repo.stars.toString(), fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.width(16.dp))

                // Forks
                Icon(
                    painter = painterResource(id = R.drawable.icon_code_fork_100_2),
                    contentDescription = "Forks",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = repo.forks.toString(), fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

