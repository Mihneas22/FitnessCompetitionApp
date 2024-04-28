package com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.AuthRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    var signInResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    var createUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    init {
        getAuthStateLogin()
        getAuthStateData()
    }

    fun signUp(email: String,password: String)
    =viewModelScope.launch {
        signUpResponse = Resource.Loading
        signUpResponse = repo.signUp(email, password)
    }

    fun signIn(email: String,password: String)
    =viewModelScope.launch {
        signInResponse = Resource.Loading
        signInResponse = repo.signIn(email, password)
    }

    fun createUser(name: String,email: String,password: String)
    =viewModelScope.launch {
        createUserResponse = Resource.Loading
        createUserResponse = repo.createUser(name, email, password)
    }

    fun logOut()
    =viewModelScope.launch {
        repo.logOut()
    }

    fun getAuthStateLogin() = repo.getAuthStateLogin(viewModelScope)

    fun getAuthStateData() = repo.getAuthStateData(viewModelScope)
}