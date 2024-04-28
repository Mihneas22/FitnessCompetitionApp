package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competition
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppButton
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.AuthViewModel
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.CompetitionViewModel
import com.example.fitnesscompetitionapp.ui.theme.aquaNew
import com.example.fitnesscompetitionapp.ui.theme.blueNew
import com.example.fitnesscompetitionapp.ui.theme.lightWhiteNew
import com.example.fitnesscompetitionapp.ui.theme.whiteNew

@Composable
fun MainScreen(
    navController: NavController,
    competitionViewModel: CompetitionViewModel = hiltViewModel()
) {
    competitionViewModel.getCompetitions()
    val getCompetitions = competitionViewModel.listOfCompetitions
    Column {
        HeaderMainScreen()
        MainMenuScreen(competitionViewModel = competitionViewModel, competitionsList = getCompetitions, navController = navController)
    }
}
@Composable
fun HeaderMainScreen(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            aquaNew
        )
    ) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(start = 30.dp),
            verticalArrangement = Arrangement.Center) {
            Text(text = "Welcome",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 35.sp
            )
            Text(text = "Mihneas22",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 35.sp,
                color = lightWhiteNew
            )
        }
    }
}

@Composable
fun MainMenuScreen(
    navController: NavController,
    competitionsList: List<Competition>,
    competitionViewModel: CompetitionViewModel
){
    Card(modifier = Modifier
        .fillMaxSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            blueNew
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
        ) {
            LazyRow {
                items(competitionsList.size){
                    com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.Competition(
                        navController = navController,
                        competition = competitionsList[it]
                    )
                }
            }

            FitnessAppButton(text = "Add Competition",
                onButClick = {
                    val competition = Competition(
                        name = "Flux Street Workout",
                        location = "Iasi",
                        numOfPeople = "0",
                        date = "27.06.2024",
                        winner = "0",
                        competitors = emptyList()
                    )
                    competitionViewModel.addCompetition(competition)
                },
                color = aquaNew,
                textColor = whiteNew
            )
        }
    }
}

@Composable
fun Competition(
    navController: NavController,
    competition: Competition
){
    Card(modifier = Modifier
        .height(200.dp)
        .width(240.dp)
        .padding(start = 20.dp, end = 20.dp)
        .clickable {
            navController.navigate("CompetitionScreen?name=${competition.name}&location=${competition.location}&date=${competition.date}&numOfPeople=${competition.numOfPeople}&winner=${competition.winner}&competitors=${competition.competitors}")
        },
        colors = CardDefaults.cardColors(
            lightWhiteNew
        )
        ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 20.dp),
        ) {
            Text(text = "Name: " + competition.name,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Date: " + competition.date,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(modifier = Modifier.padding(top = 15.dp),
                text = "Location: " + competition.location,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(modifier = Modifier.padding(top = 15.dp),
                text = "People: " + competition.numOfPeople,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}