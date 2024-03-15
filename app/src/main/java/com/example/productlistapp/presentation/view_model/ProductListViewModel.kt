package com.example.productlistapp.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.productlistapp.data.paging.ProductPagingSource
import com.example.productlistapp.domain.interactors.ProductInteractor
import javax.inject.Inject


class ProductListViewModel (private val productInteractor: ProductInteractor) : ViewModel() {

    val productList = Pager(PagingConfig(pageSize = 20)) {
        productInteractor.getProduct()
    }.flow.cachedIn(viewModelScope)


    class Factory @Inject constructor(
        private val productInteractor: ProductInteractor
    ): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ProductListViewModel::class.java)
            return ProductListViewModel(productInteractor) as T
        }
    }

}