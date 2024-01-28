package com.example.onlineshoppingapp.data.api

import com.example.onlineshoppingapp.data.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("products/categories")
    fun getCategories(): Call<List<String>>

    @GET("products/category/{category}")
    fun getProductsByCategory(
        @Path("category") category: String,
    ): Call<List<Product>>

}