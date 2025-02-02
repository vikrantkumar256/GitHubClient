package com.example.githubclient.data.mapper

import com.example.githubclient.data.model.RepoDto
import com.example.githubclient.domain.model.Repo
import javax.inject.Inject

/**
 * This class maps data from RepoDto (data transfer object)
 * to Repo (domain model).
 */
class RepoMapper @Inject constructor() {
    fun toDomain(dto: RepoDto): Repo {
        return Repo(
            name = dto.name,
            description = dto.description,
            url = dto.url,
            language = dto.language,
            stars = dto.stars,
            forks = dto.forks
        )
    }
}