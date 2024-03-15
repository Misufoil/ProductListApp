package com.example.productlistapp.di

import com.example.productlistapp.domain.interactors.ProductInteractor
import com.example.productlistapp.domain.interactors.ProductInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindProductInteractor(interactor: ProductInteractorImpl): ProductInteractor
}