package com.example.dailypulse.android.di

import com.example.dailypulse.articles.presentation.ArticlesViewModel
import com.example.dailypulse.source.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}