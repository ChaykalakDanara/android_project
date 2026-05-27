package com.chaykalak.mycookbook.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chaykalak.mycookbook.ui_model.DetailsScreen
import com.chaykalak.mycookbook.ui_model.HomeScreen
import com.chaykalak.mycookbook.viewmodel.RecipesUiState
import com.chaykalak.mycookbook.viewmodel.RecipesViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CookbookNavHost(
    navController: NavHostController,
    viewModel: RecipesViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                uiState = viewModel.uiState.value,
                onRecipeClick = { recipeId: Int ->
                    viewModel.selectRecipe(recipeId)
                    navController.navigate(Screen.Details.passId(recipeId))
                },
                onRefreshClick = { viewModel.refreshData() }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId")
            val recipe = if (recipeId != null) viewModel.selectedRecipe.value else null
            DetailsScreen(
                recipe = recipe,
                onBackPressed = {
                    viewModel.clearSelectedRecipe()
                    navController.popBackStack()
                }
            )
        }
    }
}
