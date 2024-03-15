package com.example.productlistapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productlistapp.data.retrofit.ProductApi
import com.example.productlistapp.data.mapper.toVo
import com.example.productlistapp.presentation.view_object.ProductInListVO
import retrofit2.HttpException
import javax.inject.Inject

class ProductPagingSource @Inject constructor(
    private val productApi: ProductApi
) : PagingSource<Int, ProductInListVO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductInListVO> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize
        val currentStartProduct = (page - 1) * pageSize

        Log.d("ProductPagingSource", "$currentStartProduct")

        val response = productApi.getProduct(currentStartProduct, pageSize)

//        val response =
//            RetrofitInstance.api.getProduct(currentStartProduct, pageSize)

        return if (response.isSuccessful) {
            val products = checkNotNull(response.body()).products.map { it.toVo() }

            val nextKey = if (products.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(data = products, prevKey = prevKey, nextKey = nextKey)
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