package com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.UserRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {

    var user = mutableStateOf(User("","",""))
    fun getUserData(email: String)
    =viewModelScope.launch {
        user.value = repo.getUserData(email)
    }

    fun applyForComp(competition: Competition, nameOfComp: String, name: String, email: String)
    =viewModelScope.launch {
        repo.applyForCompetition(competition, nameOfComp, name, email)
    }
}