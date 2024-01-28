package com.example.onlineshoppingapp.data.repository.search

import com.example.onlineshoppingapp.data.model.Product
import retrofit2.Call

interface SearchRepository {

    fun getProducts(): Call<List<Product>>

    fun getProductsByCategory(category: String): Call<List<Product>>

    fun getCategories(): Call<List<String>>

}