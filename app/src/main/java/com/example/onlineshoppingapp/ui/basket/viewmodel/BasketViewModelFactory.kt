package com.example.onlineshoppingapp.ui.basket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshoppingapp.data.repository.basket.BasketRepository

class BasketViewModelFactory(private val basketRepository: BasketRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BasketViewModel(basketRepository) as T
    }
}