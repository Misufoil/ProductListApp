package com.example.productlistapp.presentation

import android.app.Application
import android.content.Context
import com.example.productlistapp.di.AppComponent
import com.example.productlistapp.di.DaggerAppComponent

class ProductApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is ProductApp -> appComponent
        else -> (applicationContext as ProductApp).appComponent
    }