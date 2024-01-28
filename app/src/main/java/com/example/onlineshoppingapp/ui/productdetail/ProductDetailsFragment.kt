package com.example.onlineshoppingapp.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.model.DataState
import com.example.onlineshoppingapp.data.model.Product
import com.example.onlineshoppingapp.data.repository.basket.BasketRepositoryImpl
import com.example.onlineshoppingapp.databinding.FragmentProductDetailsBinding
import com.example.onlineshoppingapp.ui.loadingprogress.LoadingProgressBar
import com.example.onlineshoppingapp.ui.productdetail.viewmodel.ProductDetailViewModel
import com.example.onlineshoppingapp.ui.productdetail.viewmodel.ProductDetailViewModelFactory
import com.example.onlineshoppingapp.utils.Constants.PRODUCT_MODEL_NAME

class ProductDetailsFragment : Fragment() {

    private lateinit var bnd: FragmentProductDetailsBinding
    private lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel by viewModels<ProductDetailViewModel> {
        ProductDetailViewModelFactory(
            BasketRepositoryImpl()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bnd = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        getBundleArguments()
        bnd.viewModel = viewModel
        bnd.productDetailsFragment = this

        loadingProgressBar = LoadingProgressBar(requireContext())

        viewModel.addBasketLiveData.observe(viewLifecycleOwner){

            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    Toast.makeText(context, getString(R.string.product_added_basket_message), Toast.LENGTH_SHORT).show()
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(bnd.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }

        }

    }

    private fun getBundleArguments(){

        arguments?.let {

            val productData = it.getString(PRODUCT_MODEL_NAME)

            // null state check of data
            productData?.let { safeData ->

                val product = Product.fromJson(safeData)
                bnd.dataHolder = product

                viewModel.productCountLiveData.observe(viewLifecycleOwner){ value ->
                    bnd.basketCount = value
                }

            }
        }

    }

    fun goBack(){
        findNavController().popBackStack()
    }

}