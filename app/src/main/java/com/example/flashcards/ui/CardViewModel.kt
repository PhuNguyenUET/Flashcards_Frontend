package com.example.flashcards.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.CardUiState
import com.example.flashcards.ui.components.CardList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    val lists = mutableListOf<CardList>()
    private val _uiState = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()
    var currentNumOfList = 0;

    fun checkLogin(currentUserName: String, currentPassword: String) {
        if (currentUserName != uiState.value.username || currentPassword != uiState.value.password) {
            _uiState.update { currentState ->
                currentState.copy(correctLogIn = false)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(correctLogIn = true)
            }
        }
    }

    fun addInfo (currentUserName: String, currentPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(username = currentUserName, password = currentPassword)
        }
    }

    fun checkUsernameUnique (currentUserName: String): Boolean {
        return true;
    }

    fun changeTheme () {
        val tempTheme = _uiState.value.lightTheme
        _uiState.update { currentState ->
            currentState.copy(lightTheme = !tempTheme)
        }
    }

    fun setProfilePicture (@DrawableRes profilePicId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                profilePicId = profilePicId
            )
        }
    }

    fun setUsername (username: String) {
        _uiState.update { currentState ->
            currentState.copy (
                username = username
            )
        }
    }

    fun setPassword (password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password
            )
        }
    }

    fun addList (cardList: CardList) {
        lists.add(cardList)
        currentNumOfList ++
        _uiState.update { currentState ->
            currentState.copy(
                numOfList = currentNumOfList
            )
        }
    }

    fun removeList (cardList: CardList) {
        lists.remove(cardList)
        currentNumOfList --
        _uiState.update { currentState ->
            currentState.copy(
                numOfList = currentNumOfList
            )
        }
    }

    fun addWordToList () {}

    fun removeWordFromList () {}
}