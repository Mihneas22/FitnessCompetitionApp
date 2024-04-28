package com.example.fitnesscompetitionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.AuthScreens.LoginInScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.AuthScreens.SignUpScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.MainScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.AuthViewModel
import com.example.fitnesscompetitionapp.ui.theme.FitnessCompetitionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessCompetitionAppTheme {
                val currentUserSign = authViewModel.getAuthStateLogin().collectAsState().value
                val currentUserData = authViewModel.getAuthStateData().collectAsState().value

                val navController = rememberNavController()
                val startDest = remember{
                    mutableStateOf("")
                }

                if (!currentUserSign)
                    startDest.value = "MainScreen"
                else
                    startDest.value = "LoginInScreen"

                NavHost(navController = navController, startDestination = startDest.value) {
                    composable("SignUpScreen"){
                        SignUpScreen(navController = navController)
                    }

                    composable("LoginInScreen"){
                        LoginInScreen(navController = navController)
                    }

                    composable("MainScreen"){
                        MainScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessCompetitionAppTheme {

    }
}