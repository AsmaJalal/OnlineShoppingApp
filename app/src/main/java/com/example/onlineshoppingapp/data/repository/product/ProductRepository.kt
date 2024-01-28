package com.example.onlineshoppingapp.data.repository.product

import com.example.onlineshoppingapp.data.model.Product
import retrofit2.Call

interface ProductRepository {

    fun getProducts(): Call<List<Product>>

}