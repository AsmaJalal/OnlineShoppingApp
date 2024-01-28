package com.example.onlineshoppingapp.data.repository.basket

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.example.onlineshoppingapp.data.model.Product
import com.example.onlineshoppingapp.data.model.ProductBasket
import retrofit2.Call

interface BasketRepository {

    fun getAllProductsBasket(): CollectionReference

    fun getTargetProductsBasket(productBasket: ProductBasket): DocumentReference

    fun addProductsToBasket(productBasket: ProductBasket): Task<Void>

    fun deleteProducts(productBasket: ProductBasket): Task<Void>

    fun updateProductsPiece(productBasket: ProductBasket): Task<Void>

}