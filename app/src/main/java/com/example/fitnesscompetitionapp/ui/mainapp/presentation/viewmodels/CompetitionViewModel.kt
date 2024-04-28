package com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.CompetitionRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val repo: CompetitionRepository
): ViewModel(){

    var listOfCompetitions = mutableListOf<Competition>()

    fun getCompetitions()
    =viewModelScope.launch {
        listOfCompetitions =repo.getCompetitions().toMutableList()
    }

    fun addCompetition(competition: Competition)
    =viewModelScope.launch {
        repo.addCompetition(competition)
    }
}