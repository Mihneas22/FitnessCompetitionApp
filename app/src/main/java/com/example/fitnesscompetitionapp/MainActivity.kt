package com.example.fitnesscompetitionapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesscompetitionapp.ui.mainapp.domain.models.User
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.AuthScreens.LoginInScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.AuthScreens.SignUpScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.CompetitionScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.MainScreen
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.AuthViewModel
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.UserViewModel
import com.example.fitnesscompetitionapp.ui.theme.FitnessCompetitionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessCompetitionAppTheme {
                val currentUserSign = authViewModel.getAuthStateLogin().collectAsState().value
                val currentUserData = authViewModel.getAuthStateData().collectAsState().value
                val user = User("","","")

                val navController = rememberNavController()
                val startDest = remember{
                    mutableStateOf("")
                }


                if (!currentUserSign)
                    startDest.value = "MainScreen"
                else
                    startDest.value = "LoginInScreen"

                if(!currentUserSign)
                {
                    userViewModel.getUserData(currentUserData?.email.toString())
                    user.email = userViewModel.user.value.email
                    user.name = userViewModel.user.value.name
                    user.password = userViewModel.user.value.password
                }

                Log.d("userData", user.toString())

                NavHost(navController = navController, startDestination = startDest.value) {
                    composable("SignUpScreen"){
                        SignUpScreen(navController = navController)
                    }

                    composable("LoginInScreen"){
                        LoginInScreen(navController = navController)
                    }

                    composable("MainScreen"){
                        MainScreen(navController = navController,name = user.name, email = user.email)
                    }

                    composable("CompetitionScreen?nameUser={nameUser}&emailUser={emailUser}&name={name}&location={location}&date={date}&numOfPeople={numOfPeople}&winner={winner}&competitors={competitors}",
                        arguments = listOf(
                            navArgument(name = "nameUser"){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(name = "emailUser"){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(name = "name"){
                                    type = NavType.StringType
                                    nullable = true
                                },
                            navArgument(name = "location"){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(name = "date"){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(name = "winner"){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(name = "competitors"){
                                type = NavType.StringArrayType
                                nullable = true
                            }
                            )
                        ){backstackEntry->
                        CompetitionScreen(
                            nameUser = backstackEntry.arguments?.getString("nameUser")!!,
                            emailUser = backstackEntry.arguments?.getString("emailUser")!!,
                            name = backstackEntry.arguments?.getString("name")!!,
                            location = backstackEntry.arguments?.getString("location")!!,
                            date = backstackEntry.arguments?.getString("date")!!,
                            winner = backstackEntry.arguments?.getString("winner")!!,
                            competitors = backstackEntry.arguments?.getStringArray("competitors")!!,
                            navController = navController
                        )
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