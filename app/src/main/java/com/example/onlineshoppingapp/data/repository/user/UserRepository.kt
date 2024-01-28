package com.example.onlineshoppingapp.data.repository.user

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.example.onlineshoppingapp.data.model.User

interface UserRepository {

    fun getUserData(user: User): DocumentReference

    fun userAddDatabase(user: User): Task<Void>

}