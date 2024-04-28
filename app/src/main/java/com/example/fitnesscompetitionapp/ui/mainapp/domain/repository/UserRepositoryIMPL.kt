package com.example.fitnesscompetitionapp.ui.mainapp.domain.repository

import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.UserRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): UserRepository {
    override suspend fun getUserData(email: String): User {
        val db = fb.collection("users").document(email).get().await().data
        return User(
            db?.get("user_name") as String,
            db["user_email"] as String,
            db["user_password"] as String
        )
    }
}