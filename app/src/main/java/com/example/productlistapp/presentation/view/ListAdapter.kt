package com.example.productlistapp.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlistapp.R
import com.example.productlistapp.databinding.ItemProductBinding
import com.example.productlistapp.presentation.view_object.ProductInListVO

class ListAdapter :
    PagingDataAdapter<ProductInListVO, ListAdapter.MyViewHolder>(ProductDifferCallBack) {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemProductBinding.bind(view)
        fun bind(product: ProductInListVO?) {

            binding.apply {
                Glide.with(productIV.context)
                    .load(product?.thumbnail)
                    .into(productIV)

                titleTV.text = product?.title
                descriptionTV.text = product?.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object ProductDifferCallBack : DiffUtil.ItemCallback<ProductInListVO>() {
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