package com.example.productlistapp.data.repositoriesImpl

import androidx.paging.PagingSource
import com.example.productlistapp.data.paging.ProductPagingSource
import com.example.productlistapp.domain.repositories.ProductRepository
import com.example.productlistapp.presentation.view_object.ProductInListVO
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
   private val productPagingSource: ProductPagingSource
): ProductRepository {
    override fun getProduct(): PagingSource<Int, ProductInListVO> {
        return productPagingSource
    }
}