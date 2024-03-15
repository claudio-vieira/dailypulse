package com.example.dailypulse.articles

data class ArticleState (
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
