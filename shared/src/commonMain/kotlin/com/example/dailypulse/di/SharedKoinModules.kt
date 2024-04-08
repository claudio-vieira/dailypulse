package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule
)