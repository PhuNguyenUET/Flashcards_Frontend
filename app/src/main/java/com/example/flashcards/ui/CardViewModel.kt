package com.example.flashcards.ui

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.CardUiState
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.FlashCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

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

    fun changeList (idx: Int, words: List<FlashCard>, title: String, description: String) {
        var tempList: MutableList<CardList> = _uiState.value.lists
        val newList = CardList(words = words, description = description, title = title, photoId = tempList[idx].photoId)
        tempList[idx] = newList
        _uiState.update { currentState ->
            currentState.copy(lists = tempList)
        }
    }

    fun changeStar(idx: Int) {
        var tempList: MutableList<CardList> = _uiState.value.lists
        tempList[idx].setStar()
        _uiState.update { currentState ->
            currentState.copy(lists = tempList)
        }
    }

    fun checkUsernameUnique (currentUserName: String): Boolean {
        return true
    }

    fun changeTheme () {
        val tempTheme = _uiState.value.lightTheme
        _uiState.update { currentState ->
            currentState.copy(lightTheme = !tempTheme)
        }
    }

    fun setProfilePicture (@DrawableRes profilePicId: Int) {
        var tempList: MutableList<CardList> = _uiState.value.lists
        for (list in tempList) {
            list.changeId(profilePicId)
        }
        _uiState.update { currentState ->
            currentState.copy(
                profilePicId = profilePicId,
                lists = tempList
            )
        }
    }

    fun login() {
        _uiState.update {currentState ->
            currentState.copy(
                login = true
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
        var tempList: MutableList<CardList> = _uiState.value.lists
        tempList.add(cardList)
        _uiState.update { currentState ->
            currentState.copy(
                lists = tempList
            )
        }
    }

    fun removeList (cardList: CardList) {
        var tempList: MutableList<CardList> = _uiState.value.lists
        tempList.remove(cardList)
        _uiState.update { currentState ->
            currentState.copy(
                lists = tempList
            )
        }
    }

    fun removeListByIdx (idx: Int) {
        var tempList: MutableList<CardList> = _uiState.value.lists
        tempList.removeAt(idx)
        _uiState.update { currentState ->
            currentState.copy(
                lists = tempList
            )
        }
    }

    fun logOut () {
        _uiState.update { currentState ->
            currentState.copy(
                login = false
            )
        }
    }

    fun updateIdx (idx: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                idx = idx
            )
        }
    }
}