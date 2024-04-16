package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.data.ArticlesDataSource
import com.example.dailypulse.articles.data.ArticlesRepository
import com.example.dailypulse.articles.data.ArticlesService
import com.example.dailypulse.articles.application.ArticlesUseCase
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}