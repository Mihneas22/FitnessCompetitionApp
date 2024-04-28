package com.example.fitnesscompetitionapp.ui.mainapp.domain.models

data class Competition(
    val name: String = "",
    val location: String = "",
    val numOfPeople: String = "",
    val date: String = "",
    val winner: String = "",
    val competitors: List<Competitor> = emptyList()
)
