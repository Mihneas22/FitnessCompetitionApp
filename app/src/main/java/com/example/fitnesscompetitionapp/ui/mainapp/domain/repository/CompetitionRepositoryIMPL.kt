package com.example.fitnesscompetitionapp.ui.mainapp.domain.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.fitnesscompetitionapp.ui.mainapp.data.repository.CompetitionRepository
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competitor
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CompetitionRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): CompetitionRepository {

    override suspend fun getCompetitions(): List<Competition> {
        val newList = mutableListOf<Competition>()
        val db = fb.collection("competitions").get().await().documents
        for(document in db){
            val competitionNew = Competition(
                (document.data?.get("name") as String),
                (document.data?.get("location") as String),
                (document.data?.get("numOfPeople") as String),
                (document.data?.get("date") as String),
                (document.data?.get("winner") as String),
                (document.data?.get("competitors") as List<Competitor>)
            )
            newList.add(competitionNew)
        }
        return newList
    }
    override suspend fun addCompetition(competition: Competition): Resource<Boolean>
    =try{
        val competitionMap = mutableMapOf<String,Any>()
        competitionMap["name"] = competition.name
        competitionMap["date"] = competition.date
        competitionMap["location"] = competition.location
        competitionMap["winner"] = competition.winner
        competitionMap["competitors"] = competition.competitors
        competitionMap["numOfPeople"] = competition.numOfPeople
        fb.collection("competitions").document(competition.name).set(competitionMap)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getAllCompetitors(name: String): List<HashMap<String,Any>> {
        val db = fb.collection("competitions").document(name).get().await().data
        return db?.get("competitors") as List<HashMap<String,Any>>
    }
}