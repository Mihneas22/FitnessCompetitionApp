package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.Competitor
import com.example.fitnesscompetitionapp.ui.theme.blueNew

@Preview
@Composable
fun AdminDashboardScreen(
    /*nameOfComp: String,
    competitorList: List<Competitor>*/
){
    Card(modifier = Modifier
        .fillMaxSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            blueNew
        )
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, top = 25.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Name",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp
                )
            }
        }
    }
}