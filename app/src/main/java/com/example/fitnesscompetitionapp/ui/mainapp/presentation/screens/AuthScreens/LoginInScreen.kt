package com.example.fitnesscompetitionapp.ui.mainapp.presentation.screens.AuthScreens

import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnesscompetitionapp.R
import com.example.fitnesscompetitionapp.ui.mainapp.domain.util.Resource
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppButton
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppPasswordTextField
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.components.FitnessAppTextField
import com.example.fitnesscompetitionapp.ui.mainapp.presentation.viewmodels.AuthViewModel
import com.example.fitnesscompetitionapp.ui.theme.blueNew
import com.example.fitnesscompetitionapp.ui.theme.whiteNew

@Composable
fun LoginInScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            whiteNew
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                painter = painterResource(id = R.drawable.workoutman2),
                contentDescription = "WorkingMan",
                contentScale = ContentScale.FillBounds
            )

            Card(modifier = Modifier
                .padding(top = 30.dp,start = 30.dp, end = 30.dp),
                border = BorderStroke(2.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    whiteNew
                )
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription = "Icon",
                    )

                    FitnessAppTextField(
                        text = email,
                        onTextChange = {
                            if (it.all {char->
                                    char.isDefined()
                                })email=it
                        },
                        label = "Email",
                        color = whiteNew,
                        textColor = blueNew
                    )
                }
            }

            Card(modifier = Modifier
                .padding(top = 30.dp,start = 30.dp, end = 30.dp),
                border = BorderStroke(2.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    whiteNew
                )
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.Lock,
                        contentDescription = "Icon",
                    )

                    FitnessAppPasswordTextField(
                        text = password,
                        onTextChange = {
                            if (it.all {char->
                                    char.isDefined()
                                })password=it
                        },
                        label = "Password",
                        color = whiteNew,
                        textColor = blueNew,
                        visualState = passwordVisible,
                        icon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val description = if (passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = image, description)
                            }
                        }
                    )
                }
            }

            FitnessAppButton(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(300.dp)
                    .height(50.dp),
                text = "Sign In",
                onButClick = {
                    if(password.isNotEmpty() && email.isNotEmpty()){
                        authViewModel.signIn(email, password)
                        when(val signInResponse = authViewModel.signInResponse){
                            is Resource.Loading -> ProgressBar(context)

                            is Resource.Success ->{
                                Log.d("LoginFirebase","Success!")
                                navController.navigate("MainScreen")
                            }

                            is Resource.Failure -> signInResponse.apply {
                                Log.d("LoginFirebase","Error: ${ex.message}")
                                Toast.makeText(context,"Error: ${ex.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(context,"Complete all values", Toast.LENGTH_SHORT).show()
                    }
                },
                color = blueNew,
                textColor = whiteNew
            )

            Row(modifier = Modifier.padding(top = 40.dp)) {
                Text(text = "Don't have an account?",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate("SignUpScreen")
                    },
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyLarge,
                    color = blueNew
                )
            }
        }
    }
}