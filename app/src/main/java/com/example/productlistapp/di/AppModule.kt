package com.example.productlistapp.di

import dagger.Module

@Module(includes = [NetworkModule::class, InteractorModule::class, RepositoryModule::class])
object AppModule