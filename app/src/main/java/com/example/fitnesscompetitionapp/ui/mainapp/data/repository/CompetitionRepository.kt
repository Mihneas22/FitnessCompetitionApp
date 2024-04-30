package com.example.fitnesscompetitionapp.ui.mainapp.data.repository

import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competitor
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CompetitionRepository {
    suspend fun getCompetitions(): List<Competition>

    suspend fun addCompetition(competition: Competition): Resource<Boolean>

    suspend fun getAllCompetitors(name: String): List<HashMap<String,Any>>
}