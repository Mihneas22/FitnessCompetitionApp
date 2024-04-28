package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppButton
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.AuthViewModel
import com.example.fitnesscompetitionapp.ui.theme.blueNew

@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    Card(modifier = Modifier.fillMaxSize()) {
        Text(text = "HellOOOOO")

        FitnessAppButton(text = "",
            onButClick = {
                navController.navigate("LoginInScreen")
                authViewModel.logOut()
            },
            color = blueNew, textColor = Color.White)
    }
}