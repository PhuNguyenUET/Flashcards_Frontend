package com.example.flashcards

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.flashcards.ui.*
import com.example.flashcards.ui.components.CardList
import com.example.flashcards.ui.components.TopAppBar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.navigation

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
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlashcardApp (
    modifier: Modifier = Modifier,
    viewModel: CardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberAnimatedNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    FlashcardScreen.valueOf(
        backStackEntry?.destination?.route?: if (uiState.login) FlashcardScreen.Home.name else FlashcardScreen.PreloginHome.name
    )
    val normalEnter: EnterTransition = fadeIn(animationSpec = tween(700))
    val normalExit: ExitTransition = fadeOut(animationSpec = tween(700)) + slideOutHorizontally(animationSpec = tween(700))
    val optionEnter: EnterTransition = slideInHorizontally(animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
    val optionExit: ExitTransition = slideOutHorizontally(animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
    val loginEnter: EnterTransition = fadeIn(initialAlpha = 0.3f)
    val loginExit: ExitTransition = fadeOut()
    Scaffold (
        topBar = {
            if (uiState.login) {
                TopAppBar(
                    profileId = uiState.profilePicId,
                    onMenuClicked = { navController.navigate(FlashcardScreen.MainNavigationScreen.name) },
                    onAddClicked = {
                        navController.navigate(FlashcardScreen.AddMoreScreen.name)
                        viewModel.changeNew()
                    },
                    onProfileClicked = { navController.navigate(FlashcardScreen.ProfileScreen.name) })
            }
        }
    ) { innerPadding ->
        AnimatedNavHost (
            navController = navController,
            startDestination = if (uiState.login) FlashcardScreen.Home.name else FlashcardScreen.PreloginHome.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(
                route = FlashcardScreen.AddMoreScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                AddMoreScreen(
                    cardList = if (!uiState.createNew) uiState.lists[uiState.idx] else CardList(),
                    idx = uiState.idx,
                    onDoneClicked = { navController.navigateUp() },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.CardDisplayScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                CardDisplayScreen(
                    cardList = uiState.lists[uiState.idx],
                    onOptionClicked = { navController.navigate(FlashcardScreen.MoreOptionScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.ChangePasswordScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                ChangePassword(onChangeClicked = { navController.navigateUp() },  cardViewModel = viewModel)
            }
            composable(route = FlashcardScreen.ChangeUsername.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                ChangeUsername(onChangeClicked = { navController.navigateUp() },  cardViewModel = viewModel)
            }
            composable(route = FlashcardScreen.Home.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                Home(
                    onCardClicked = {navController.navigate(FlashcardScreen.CardDisplayScreen.name)},
                    onEditClicked = {
                        navController.navigate(FlashcardScreen.AddMoreScreen.name)
                        viewModel.changeOld()
                                    },
                    onShareClicked = {  },
                    cardLists = uiState.lists,
                    discoveryLists = uiState.lists,
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.LoginScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                LoginScreen(
                    onSiteChanged = { navController.navigate(FlashcardScreen.RegisterScreen.name) },
                    onLoginClicked = { navController.navigate(FlashcardScreen.Home.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.MainNavigationScreen.name,
                enterTransition = {optionEnter},
                exitTransition = { optionExit }
            ) {
                MainNavigationScreen(
                    onYourListClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onProfileClicked = { navController.navigate(FlashcardScreen.ProfileScreen.name) },
                    onSettingsClicked = { navController.navigate(FlashcardScreen.SettingsScreen.name) },
                    onLogoutClicked = { navController.navigate(FlashcardScreen.PreloginHome.name)
                                      viewModel.logOut()},
                    onCancelClicked = { navController.navigateUp() },
                    onHomeClicked = { navController.navigate(FlashcardScreen.Home.name) }
                )
            }
            composable(route = FlashcardScreen.MoreOptionScreen.name,
                enterTransition = {optionEnter},
                exitTransition = { optionExit }
            ) {
                MoreOptionScreen(
                    onEditClicked = {
                        navController.navigate(FlashcardScreen.AddMoreScreen.name)
                        viewModel.changeOld()
                                    },
                    onShareClicked = { },
                    onDeleteClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onCancelClicked = { navController.navigateUp() },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.PreloginHome.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                PreLoginHome(
                    onLoginButtonClicked = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    onRegisterButtonClicked = { navController.navigate(FlashcardScreen.RegisterScreen.name) }
                )
            }
            composable(route = FlashcardScreen.ProfileChangeScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                ProfileChangeScreen(
                    onCancelClicked = { navController.navigateUp() },
                    onSaveClicked = { navController.navigateUp() },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.ProfileScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                ProfileScreen(
                    onPhotoClicked = { navController.navigate(FlashcardScreen.ProfileChangeScreen.name) },
                    onCardListClicked = { navController.navigate(FlashcardScreen.YourListScreen.name) },
                    onSettingsClicked = { navController.navigate(FlashcardScreen.SettingsScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.RegisterScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                RegisterScreen(
                    onSiteChanged = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    onSignupClicked = { navController.navigate(FlashcardScreen.LoginScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.SettingsScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                SettingsScreen(
                    onUsernameChangeClicked = { navController.navigate(FlashcardScreen.ChangeUsername.name) },
                    onPasswordChangeClicked = { navController.navigate(FlashcardScreen.ChangePasswordScreen.name) },
                    cardViewModel = viewModel
                )
            }
            composable(route = FlashcardScreen.YourListScreen.name,
                enterTransition = {normalEnter},
                exitTransition = { normalExit }
            ) {
                YourListScreen(
                    onCardClicked = { navController.navigate(FlashcardScreen.CardDisplayScreen.name) },
                    onEditClicked = {
                        navController.navigate(FlashcardScreen.AddMoreScreen.name)
                        viewModel.changeOld()
                                    },
                    onShareClicked = {  },
                    onAddMoreClicked = { navController.navigate(FlashcardScreen.AddMoreScreen.name) },
                    cardLists = uiState.lists,
                    cardViewModel = viewModel
                )
            }
        }
    }
}