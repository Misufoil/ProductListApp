package com.example.productlistapp.di

import com.example.productlistapp.presentation.view.ProductListFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: ProductListFragment)
}