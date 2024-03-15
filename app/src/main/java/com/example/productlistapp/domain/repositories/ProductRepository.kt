package com.example.productlistapp.domain.repositories

import androidx.paging.PagingSource
import com.example.productlistapp.presentation.view_object.ProductInListVO

interface ProductRepository {
    fun getProduct(): PagingSource<Int, ProductInListVO>
}