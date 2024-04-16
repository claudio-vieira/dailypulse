package com.example.dailypulse.source.di

import com.example.dailypulse.source.application.SourcesUseCase
import com.example.dailypulse.source.data.SourcesDataSource
import com.example.dailypulse.source.data.SourcesRepository
import com.example.dailypulse.source.data.SourcesService
import com.example.dailypulse.source.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {

    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
}