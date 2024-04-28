package com.example.fitnesscompetitionapp.ui.mainapp.data.repository

import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User

interface UserRepository {
    suspend fun getUserData(email: String): User
}