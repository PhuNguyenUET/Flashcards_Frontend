package com.example.flashcards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flashcards.ui.*
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.TopAppBar


enum class FlashcardScreen {
    AddMoreScreen,
    CardDisplayScreen,
    ChangePasswordScreen,
    ChangeUsername,
    Home,
    LoginScreen,
    MainNavigationScreen,
    MoreOptionScreen,
    PreloginHome,
    ProfileChangeScreen,
    ProfileScreen,
    RegisterScreen,
    SettingsScreen,
    YourListScreen
}
@Composable
fun FlashcardApp (
    modifier: Modifier = Modifier,
    viewModel: CardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    FlashcardScreen.valueOf(
        backStackEntry?.destination?.route?: if (uiState.login) FlashcardScreen.Home.name else FlashcardScreen.PreloginHome.name
    )
    Scaffold (
        topBar = {
            if (uiState.login) {
                TopAppBar(
                    profileId = uiState.profilePicId,
                    onMenuClicked = { navController.navigate(FlashcardScreen.MainNavigationScreen.name) },
                    onAddClicked = {
                        navController.navigate(FlashcardScreen.AddMoreScreen.name)
                        val list = CardList()
                        viewModel.addList(list)
                        viewModel.updateIdx(uiState.lists.size - 1)
                    },
                    onProfileClicked = { navController.navigate(FlashcardScreen.ProfileScreen.name) })
            }
        }
    ) { innerPadding ->
        NavHost (
            navController = navController,
            startDestination = if (uiState.login) FlashcardScreen.Home.name else FlashcardScreen.PreloginHome.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = FlashcardScreen.AddMoreScreen.name) {
                AddMoreScreen(
                    cardList = uiState.lists[uiState.idx],
                    idx = uiState.idx,
                    onDoneClicked = { navController.navigateUp() },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.CardDisplayScreen.name) {
                CardDisplayScreen(
                    cardList = uiState.lists[uiState.idx],
                    onOptionClicked = { navController.navigate(FlashcardScreen.MoreOptionScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.ChangePasswordScreen.name) {
                ChangePassword(onChangeClicked = { navController.navigateUp() },  cardViewModel = viewModel)
            }
            composable(route = FlashcardScreen.ChangeUsername.name) {
                ChangeUsername(onChangeClicked = { navController.navigateUp() },  cardViewModel = viewModel)
            }
            composable(route = FlashcardScreen.Home.name) {
                Home(
                    onCardClicked = {navController.navigate(FlashcardScreen.CardDisplayScreen.name)},
                    onEditClicked = { navController.navigate(FlashcardScreen.AddMoreScreen.name) },
                    onShareClicked = {  },
                    cardLists = uiState.lists,
                    discoveryLists = uiState.lists,
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.LoginScreen.name) {
                LoginScreen(
                    onSiteChanged = { navController.navigate(FlashcardScreen.RegisterScreen.name) },
                    onLoginClicked = { navController.navigate(FlashcardScreen.Home.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.MainNavigationScreen.name) {
                MainNavigationScreen(
                    onYourListClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onProfileClicked = { navController.navigate(FlashcardScreen.ProfileScreen.name) },
                    onSettingsClicked = { navController.navigate(FlashcardScreen.SettingsScreen.name) },
                    onLogoutClicked = { navController.navigate(FlashcardScreen.PreloginHome.name)
                                      viewModel.logOut()},
                    onCancelClicked = { navController.navigateUp() }
                )
            }
            composable(route = FlashcardScreen.MoreOptionScreen.name) {
                MoreOptionScreen(
                    onEditClicked = { navController.navigate(FlashcardScreen.AddMoreScreen.name) },
                    onShareClicked = { },
                    onDeleteClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onCancelClicked = { navController.navigateUp() }
                )
            }
            composable(route = FlashcardScreen.PreloginHome.name) {
                PreLoginHome(
                    onLoginButtonClicked = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    onRegisterButtonClicked = { navController.navigate(FlashcardScreen.RegisterScreen.name) }
                )
            }
            composable(route = FlashcardScreen.ProfileChangeScreen.name) {
                ProfileChangeScreen(
                    onCancelClicked = { navController.navigateUp() },
                    onSaveClicked = { navController.navigateUp() },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.ProfileScreen.name) {
                ProfileScreen(
                    onPhotoClicked = { navController.navigate(FlashcardScreen.ProfileScreen.name) },
                    onCardListClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onSettingsClicked = { navController.navigate(FlashcardScreen.SettingsScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.RegisterScreen.name) {
                RegisterScreen(
                    onSiteChanged = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    onSignupClicked = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.SettingsScreen.name) {
                SettingsScreen(
                    onUsernameChangeClicked = { navController.navigate(FlashcardScreen.ChangeUsername.name) },
                    onPasswordChangeClicked = { navController.navigate(FlashcardScreen.ChangePasswordScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.YourListScreen.name) {
                YourListScreen(
                    onCardClicked = { navController.navigate(FlashcardScreen.CardDisplayScreen.name) },
                    onEditClicked = { navController.navigate(FlashcardScreen.AddMoreScreen.name) },
                    onShareClicked = {  },
                    onAddMoreClicked = { navController.navigate(FlashcardScreen.AddMoreScreen.name) },
                    cardLists = uiState.lists,
                    cardViewModel = viewModel
                )
            }
        }
    }
}