package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel: BaseViewModel() {

    private val _articlesState = MutableStateFlow(ArticlesState(loading = true))
    val articlesState = _articlesState

    private val useCase: ArticlesUseCase

    init {

        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json{
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val service = ArticlesService(httpClient)
        useCase = ArticlesUseCase(service)
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = useCase.getArticles()
            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }
}