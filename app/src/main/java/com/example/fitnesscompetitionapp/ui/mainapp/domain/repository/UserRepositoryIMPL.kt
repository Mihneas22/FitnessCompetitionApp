package com.example.fitnesscompetitionapp.ui.mainapp.domain.repository

import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.UserRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
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

    override suspend fun applyForCompetition(competition: Competition,nameOfComp: String,name: String, email: String): Resource<Boolean>
    =try{
        val competitor = mutableMapOf<String,Any>()
        competitor["competitor_name"] = name
        competitor["competitor_email"] = email
        competitor["competitor_points"] = 0

        val competitionMap = mutableMapOf<String,Any>()
        competitionMap["name"] = competition.name
        competitionMap["date"] = competition.date
        competitionMap["location"] = competition.location
        competitionMap["winner"] = competition.winner
        competitionMap["competitors"] = competition.competitors + competitor
        competitionMap["numOfPeople"] = competition.numOfPeople

        fb.collection("competitions").document(nameOfComp).set(competitionMap)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }
}