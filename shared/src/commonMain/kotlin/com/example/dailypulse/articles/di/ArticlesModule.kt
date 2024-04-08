package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.ArticlesService
import com.example.dailypulse.articles.ArticlesUseCase
import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}