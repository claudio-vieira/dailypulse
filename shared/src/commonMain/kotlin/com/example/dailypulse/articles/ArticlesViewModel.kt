package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.ResponseState
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

    fun getArticles(forceFetch: Boolean = false, endRefresh: () -> Unit = {}) {
        scope.launch {
            _articlesState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )
            when(val result = useCase.getArticles(forceFetch)) {
                is ResponseState.Success -> {
                    _articlesState.emit(ArticlesState(articles = result.item))
                    endRefresh.invoke()
                }
                is ResponseState.Error -> TODO()
                ResponseState.Loading -> TODO()
            }
        }
    }
}