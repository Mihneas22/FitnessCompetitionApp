package com.example.fitnesscompetitionapp.ui.mainapp.data.repository

import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource

interface UserRepository {
    suspend fun getUserData(email: String): User

    suspend fun applyForCompetition(competition: Competition, nameOfComp: String, name: String, email: String): Resource<Boolean>
}