package com.example.productlistapp.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.productlistapp.paging.ProductPagingSource


class ProductListViewModel : ViewModel() {

    val productList = Pager(PagingConfig(20,)) {
        ProductPagingSource()
    }.flow.cachedIn(viewModelScope)


}