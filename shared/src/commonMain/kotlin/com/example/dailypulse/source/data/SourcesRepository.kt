package com.example.dailypulse.source.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {
    suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {

        if(forceFetch) {
            dataSource.clearSources()
            return fetchSources()
        }

        val sourcesDb = dataSource.getAllSources()

        if(sourcesDb.isEmpty()) {
            return fetchSources()
        }

        return sourcesDb
    }

    private suspend fun fetchSources(): List<SourceRaw> {
        val fetchedSources = service.fetchSources()
        dataSource.insertSources(fetchedSources)
        return fetchedSources
    }
}