package com.example.flashcards.data

import com.example.flashcards.R
import com.example.flashcards.ui.components.CardList

data class CardUiState (
    val username: String = "skylawson",
    val password: String = "2007356",
    val name: String = "SkyLawson",
    val numOfList: Int = 0,
    val profilePicId: Int = R.drawable.profile_photo_1,
    val numOfCardsInList: List<Int> = mutableListOf(),
    val correctLogIn: Boolean = true
)