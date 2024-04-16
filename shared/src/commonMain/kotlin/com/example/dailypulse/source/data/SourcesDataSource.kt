package com.example.dailypulse.source.data

import com.example.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {

    fun getAllSources() : List<SourceRaw> =
        database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun clearSources() = database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourceRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            sourceRaw.id,
            sourceRaw.name,
            sourceRaw.description,
            sourceRaw.language,
            sourceRaw.country
        )
    }

    private fun mapToSourceRaw(
        id: String,
        name: String,
        description: String,
        language: String,
        country: String
    ) : SourceRaw =
        SourceRaw(
            id,
            name,
            description,
            language,
            country
        )
}