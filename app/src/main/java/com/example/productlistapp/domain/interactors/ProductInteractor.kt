package com.example.productlistapp.domain.interactors

import androidx.paging.PagingSource
import com.example.productlistapp.domain.repositories.ProductRepository
import com.example.productlistapp.presentation.view_object.ProductInListVO
import javax.inject.Inject

interface ProductInteractor {
    fun getProduct(): PagingSource<Int, ProductInListVO>
}

class ProductInteractorImpl @Inject constructor(private val repository: ProductRepository) :
    ProductInteractor {
    override fun getProduct(): PagingSource<Int, ProductInListVO> {
        return repository.getProduct()
    }
}