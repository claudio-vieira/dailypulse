package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articlesModule
import com.example.dailypulse.source.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    sourcesModule,
    networkModule
)