package com.example.onlineshoppingapp.ui.auth.signin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.model.DataState
import com.example.onlineshoppingapp.data.model.User
import com.example.onlineshoppingapp.data.repository.auth.AuthRepository
import com.example.onlineshoppingapp.data.repository.user.UserRepository

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {

    val userLiveData = MutableLiveData<DataState<User>>()

    fun onSignInClicked(email: String, password: String){

        userLiveData.value = DataState.Loading()
        val user = User(email, password)
        checkFields(user)

    }

    private fun checkFields(user: User){

        if(user.isSignInFieldEmpty()){
            userLiveData.value = DataState.Error(R.string.fields_cannot_empty.toString())
            return
        }

        if(!user.isValidEmail()){
            userLiveData.value = DataState.Error(R.string.enter_valid_email.toString())
            return
        }

        if(!user.isPasswordGreaterThan5()){
            userLiveData.value = DataState.Error(R.string.password_greater_than_5.toString())
            return
        }

        signIn(user)

    }

    private fun signIn(user: User){

        authRepository.signIn(user).addOnCompleteListener { task ->

            if(task.isSuccessful) {

                user.uid = task.result.user?.uid
                getUserFirestore(user)

            }else {
                userLiveData.value = DataState.Error(task.exception?.message!!)
            }

        }

    }

    private fun getUserFirestore(user: User){

        userRepository.getUserData(user).addSnapshotListener { value, error ->

            if(error == null){

                user.username = value?.toObject(User::class.java)?.username
                userLiveData.value = DataState.Success(user)

            }else{
                userLiveData.value = DataState.Error(error.message!!)
            }

        }

    }

}