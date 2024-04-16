package com.example.dailypulse.source.application

import com.example.dailypulse.ResponseState
import com.example.dailypulse.source.data.SourceRaw
import com.example.dailypulse.source.data.SourcesRepository

class SourcesUseCase(private val sourcesRepository: SourcesRepository)  {

    suspend fun getSources(forceFetch: Boolean): ResponseState<List<Source>> {
        val sourcesRaw = sourcesRepository.getSources(forceFetch)
        return ResponseState.Success(mapArticles(sourcesRaw))
    }

    private fun mapArticles(sourcesRaw: List<SourceRaw>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.description,
            raw.language,
            raw.country
        )
    }
}