package com.example.productlistapp.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productlistapp.R

class ProductLoadStateAdapter() : LoadStateAdapter<ProductLoadStateAdapter.ItemViewHolder>() {
    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ItemViewHolder {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            )

            is LoadState.Error -> ErrorViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
            )

            is LoadState.NotLoading -> error("Not supported")
        }
    }

    companion object {
        private const val ERROR = 1
        private const val PROGRESS = 0
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder internal constructor(view: View) : ItemViewHolder(view) {
        override fun bind(loadState: LoadState) {

        }
    }

    class ErrorViewHolder internal constructor(view: View) : ItemViewHolder(view) {
        private val error: TextView = view.findViewById(R.id.error_message)

        override fun bind(loadState: LoadState) {
            require(loadState is LoadState.Error)
            error.text = loadState.error.localizedMessage
        }
    }

}