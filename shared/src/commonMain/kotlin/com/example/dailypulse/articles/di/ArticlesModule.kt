package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.ArticlesDataSource
import com.example.dailypulse.articles.ArticlesRepository
import com.example.dailypulse.articles.ArticlesService
import com.example.dailypulse.articles.ArticlesUseCase
import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}