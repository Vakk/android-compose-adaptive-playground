package com.valeriik.adaptivedesign.ui.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.valeriik.adaptivedesign.ui.Navigation
import com.valeriik.adaptivedesign.ui.viewmodel.RecipesListViewModel

@Composable
fun AdaptiveExampleScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = viewModel<RecipesListViewModel>()

    NavHost(modifier = modifier, navController = navController, startDestination = Navigation.RecipesList) {
        composable<Navigation.RecipesList> {
            RecipesList(navController = navController, viewModel = viewModel)
        }
        composable<Navigation.RecipeDetails> { backStackEntry ->
            val route = backStackEntry.toRoute<Navigation.RecipeDetails>()
            RecipeDetails(
                receiptId = route.id,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}