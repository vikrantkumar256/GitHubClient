package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.RepoDto
import com.example.githubclient.domain.model.Repo
import org.junit.Test
import org.junit.Assert.*

class RepoMapperTest {

    private val repoMapper = RepoMapper()

    @Test
    fun `test toDomain maps RepoDto to Repo correctly`() {
        val repoDto = RepoDto(
            name = "Repo1",
            description = "Description of Repo1",
            url = "https://github.com/repo1",
            language = "Kotlin",
            stars = 10,
            forks = 5
        )

        val expectedRepo = Repo(
            name = "Repo1",
            description = "Description of Repo1",
            url = "https://github.com/repo1",
            language = "Kotlin",
            stars = 10,
            forks = 5
        )

        val result = repoMapper.toDomain(repoDto)

        // Assert that the result matches the expected Repo
        assertEquals(expectedRepo, result)
    }
}
