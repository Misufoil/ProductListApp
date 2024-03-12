package com.example.productlistapp.data.utils

import com.example.productlistapp.data.dto.ProductInListDTO
import com.example.productlistapp.presentation.view_object.ProductInListVO

fun ProductInListDTO.toVo(): ProductInListVO =
    ProductInListVO(
        brand = brand,
        category = category,
        description = description,
        discountPercentage = discountPercentage,
        id = id,
        images = images,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )