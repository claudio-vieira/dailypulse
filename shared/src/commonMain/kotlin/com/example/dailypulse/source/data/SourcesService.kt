package com.example.dailypulse.source.data

import com.example.dailypulse.Variables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient) {

    private val apiKey = Variables.APIKEY
    private val url = Variables.URLAPI

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourcesResponse = httpClient.get(
            "$url/v2/top-headlines/sources?apiKey=$apiKey"
        ).body()
        return response.sources
    }
}