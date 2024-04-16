package com.example.dailypulse.articles.data

import com.example.dailypulse.Variables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    private val country = Variables.COUNTRY
    private val category = Variables.CATEGORY
    private val apiKey = Variables.APIKEY
    private val url = Variables.URLAPI

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get(
            "$url/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey"
        ).body()
        return response.articles
    }
}