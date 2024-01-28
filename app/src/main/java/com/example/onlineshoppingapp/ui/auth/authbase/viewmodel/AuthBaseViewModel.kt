package com.example.onlineshoppingapp.ui.auth.authbase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.model.TabLayoutFragment
import com.example.onlineshoppingapp.ui.auth.signin.SignInFragment
import com.example.onlineshoppingapp.ui.auth.signup.SignUpFragment

class AuthBaseViewModel : ViewModel() {

    val fragmentLiveData = MutableLiveData<TabLayoutFragment>()

    init {
        initFragments()
    }

    private fun initFragments(){

        val fragments = TabLayoutFragment(
            listOf(
                SignInFragment(),
                SignUpFragment()
            ),
            listOf(
                R.string.signin,
                R.string.signup
            )
        )

        fragmentLiveData.value = fragments

    }

}