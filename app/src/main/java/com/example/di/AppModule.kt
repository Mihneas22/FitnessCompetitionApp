package com.example.di

import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.AuthRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.repository.AuthRepositoryIMPL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun AuthRepository(): AuthRepository = AuthRepositoryIMPL(
        auth = FirebaseAuth.getInstance(),
        fb = FirebaseFirestore.getInstance()
    )
}