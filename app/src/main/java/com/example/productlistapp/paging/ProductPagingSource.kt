package com.example.productlistapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productlistapp.data.retrofit.api.RetrofitInstance
import com.example.productlistapp.data.utils.toVo
import com.example.productlistapp.presentation.view_object.ProductInListVO
import retrofit2.HttpException

class ProductPagingSource : PagingSource<Int, ProductInListVO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductInListVO> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize
        val currentStartProduct = (page - 1) * pageSize

        Log.d("ProductPagingSource","$currentStartProduct")
        val response =
            RetrofitInstance.api.getProduct(currentStartProduct.toString(), pageSize.toString())

        return if (response.isSuccessful) {
            val products = checkNotNull(response.body()).products.map { it.toVo() }

            val nextKey = if (products.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(products, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductInListVO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}