package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
): BaseViewModel() {

    private val _articlesState = MutableStateFlow(ArticlesState(loading = true))
    val articlesState = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = useCase.getArticles()
            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }
}