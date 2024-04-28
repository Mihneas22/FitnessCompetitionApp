package com.example.fitnesscompetitionapp.ui.mainapp.data.repository

import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signUp(email: String, password: String): Resource<Boolean>

    suspend fun signIn(email: String, password: String): Resource<Boolean>

    suspend fun createUser(name: String,email: String,password: String): Resource<Boolean>

    fun logOut()

    fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean>

    fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?>
}