package com.example.onlineshoppingapp.ui.main.search.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.model.Product
import com.example.onlineshoppingapp.databinding.ItemProductSearchBinding
import com.example.onlineshoppingapp.utils.Constants

class SearchAdapter(
      private val navController: NavController,
      private val productList: List<Product>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var binding: ItemProductSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = ItemProductSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun goProductDetails(product: Product?){

        navController.navigate(
            R.id.action_searchFragment_to_productDetailsFragment,
            Bundle().apply {
                putString(Constants.PRODUCT_MODEL_NAME, product?.toJson())
            })

    }

    inner class SearchViewHolder(private val binding: ItemProductSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.dataHolder = product
            binding.searchAdapter = this@SearchAdapter
            binding.executePendingBindings()
        }

    }

    override fun getItemCount(): Int = productList.size

}