package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesscompetitionapp.ui.theme.blueNew
import com.example.fitnesscompetitionapp.ui.theme.whiteNew

@Composable
fun CompetitionScreen(
    name: String,
    location: String,
    date: String,
    numOfPeople: String,
    winner: String,
    competitors: Array<String>
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
                    text = "Number Of People: $numOfPeople",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Date: $date",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Competitors: ${competitors.toList()}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "Winner: $winner",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp,end = 40.dp,top = 40.dp)
                .height(500.dp)
                .verticalScroll(rememberScrollState()),
                colors = CardDefaults.cardColors(
                    whiteNew
                )
            ) {

            }
        }
    }
}