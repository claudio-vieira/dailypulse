package com.example.dailypulse.source.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.ResponseState
import com.example.dailypulse.source.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val useCase: SourcesUseCase
): BaseViewModel() {

    private val _sourcesState = MutableStateFlow(SourcesState(loading = true))
    val sourcesState = _sourcesState

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false, endRefresh: () -> Unit = {}) {
        scope.launch {
            _sourcesState.emit(
                SourcesState(
                    loading = true,
                    sources = _sourcesState.value.sources
                )
            )
            when(val result = useCase.getSources(forceFetch)) {
                is ResponseState.Success -> {
                    _sourcesState.emit(SourcesState(sources = result.item))
                    endRefresh.invoke()
                }
                is ResponseState.Error -> TODO()
                ResponseState.Loading -> TODO()
            }
        }
    }
}