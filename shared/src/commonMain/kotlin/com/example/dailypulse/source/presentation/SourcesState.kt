package com.example.dailypulse.source.presentation

import com.example.dailypulse.source.application.Source

data class SourcesState (
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
