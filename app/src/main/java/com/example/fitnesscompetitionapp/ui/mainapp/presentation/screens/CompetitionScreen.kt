package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competitor
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppButton
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.CompetitionViewModel
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.UserViewModel
import com.example.fitnesscompetitionapp.ui.theme.aquaNew
import com.example.fitnesscompetitionapp.ui.theme.blueNew
import com.example.fitnesscompetitionapp.ui.theme.whiteNew
import org.checkerframework.checker.units.qual.A

@Composable
fun CompetitionScreen(
    nameUser: String,
    emailUser: String,
    name: String,
    location: String,
    date: String,
    winner: String,
    competitors: Array<String>,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    competitionViewModel: CompetitionViewModel = hiltViewModel()
){
    competitionViewModel.getCompetitors(name)
    val listOfCompetitorsSize = competitionViewModel.listOfCompetitors.size
    val listOfCompetitors = competitionViewModel.listOfCompetitors
    listOfCompetitors.sortBy {
        it["competitor_points"] as Int
    }

    val newListOfCompetitors = mutableListOf<Competitor>()

    if(listOfCompetitors.isNotEmpty()){
        for (competitor in listOfCompetitors){
            val competitor = Competitor(
                name = competitor["competitor_name"] as String,
                email = competitor["competitor_name"] as String,
                points = competitor["competitor_points"] as Long,
            )
            newListOfCompetitors.add(competitor)
        }
        Log.d("competitorList",newListOfCompetitors.toString())
    }

    Card(modifier = Modifier
        .fillMaxSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            blueNew
        )
    ) {
        Icon(imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "Arrow",
            modifier = Modifier.clickable {
                navController.navigate("MainScreen")
            })

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 40.sp
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)) {
                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Location: $location",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Number Of People: $listOfCompetitorsSize",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Date: $date",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Winner: $winner",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )
            }

            FitnessAppButton(
                modifier = Modifier.padding(top = 20.dp),
                text = "Apply",
                onButClick = {
                    competitionViewModel.getCompetitors(name)
                    val competition = Competition(
                        name, location, listOfCompetitorsSize.toString(), date, winner, newListOfCompetitors
                    )

                    userViewModel.applyForComp(competition,name,nameUser,emailUser)
                },
                color = aquaNew,
                textColor = whiteNew)

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 40.dp)
                .height(500.dp)
                .verticalScroll(rememberScrollState()),
                colors = CardDefaults.cardColors(
                    whiteNew
                )
            ) {
                LazyRow {
                    items(newListOfCompetitors.size){
                        RankingCompetitor(competitor = newListOfCompetitors[it])
                    }
                }
            }
        }
    }
}

@Composable
fun RankingCompetitor(
    competitor: Competitor
){
    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            aquaNew
        )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Name: ${competitor.name}, ",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )

            Text(text = "Points: ${competitor.points}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
}