package com.example.productlistapp.data.retrofit

import com.example.productlistapp.data.dto.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("/products")
    suspend fun getProduct(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): Response<Product>
}