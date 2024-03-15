package com.example.productlistapp.di

import com.example.productlistapp.data.repositoriesImpl.ProductRepositoryImpl
import com.example.productlistapp.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository
}