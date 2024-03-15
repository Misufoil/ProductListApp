package com.example.productlistapp.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.productlistapp.databinding.FragmentProductListBinding
import com.example.productlistapp.presentation.appComponent
import com.example.productlistapp.presentation.view_model.ProductListViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListFragment : Fragment() {

    @Inject
    lateinit var factory: ProductListViewModel.Factory

    private var mBinding: FragmentProductListBinding? = null
    private val binding get() = mBinding!!

    private val listAdapter by lazy { ListAdapter() }

//    @Inject
//    lateinit var productInteractor: ProductInteractor
//
//    private val viewModel: ProductListViewModel by viewModelCreator {
//        ProductListViewModel(productInteractor = productInteractor)
//    }

    private val viewModel: ProductListViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(fragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProductListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvProductList.adapter = listAdapter.withLoadStateFooter(ProductLoadStateAdapter())

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.productList.collect { listAdapter.submitData(it) }
            }
        }

        listAdapter.addLoadStateListener { state ->
            binding.rvProductList.isVisible = state.refresh != LoadState.Loading
            binding.progress.isVisible = state.refresh == LoadState.Loading
        }

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}