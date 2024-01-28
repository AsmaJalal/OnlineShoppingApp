package com.example.onlineshoppingapp.data.repository.product

import com.example.onlineshoppingapp.data.api.ApiService
import com.example.onlineshoppingapp.data.model.Product
import retrofit2.Call

class ProductRepositoryImpl constructor(private val apiService: ApiService) : ProductRepository {

    override fun getProducts(): Call<List<Product>> {
        return apiService.getProducts()
    }

}