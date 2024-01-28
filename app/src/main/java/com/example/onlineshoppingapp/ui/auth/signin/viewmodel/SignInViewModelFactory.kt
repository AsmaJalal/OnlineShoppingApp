package com.example.onlineshoppingapp.ui.auth.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshoppingapp.data.repository.auth.AuthRepository
import com.example.onlineshoppingapp.data.repository.user.UserRepository

class SignInViewModelFactory(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(authRepository, userRepository) as T
    }
}