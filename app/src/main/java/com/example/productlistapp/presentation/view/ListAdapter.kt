package com.example.productlistapp.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlistapp.R
import com.example.productlistapp.presentation.view_object.ProductInListVO

class ListAdapter : PagingDataAdapter<ProductInListVO, ListAdapter.MyViewHolder>(differCallBack) {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productIV: ImageView = view.findViewById(R.id.productIV)
        val titleTV: TextView = view.findViewById(R.id.titleTV)
        val descriptionTV: TextView = view.findViewById(R.id.descriptionTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_list_item,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = getItem(position)!!
        holder.setIsRecyclable(false)

        holder.apply {
            Glide.with(productIV.context)
                .load(product.thumbnail)
                .into(productIV)

            titleTV.text = product.title
            descriptionTV.text = product.description
        }

    }

    companion object {
        val differCallBack = object : DiffUtil.ItemCallback<ProductInListVO>() {
            override fun areItemsTheSame(
                oldItem: ProductInListVO,
                newItem: ProductInListVO
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductInListVO,
                newItem: ProductInListVO
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}