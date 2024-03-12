package com.example.productlistapp.data.dto

data class Product(
    val limit: Int,
    val products: List<ProductInListDTO>,
    val skip: Int,
    val total: Int
)