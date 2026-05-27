package com.chaykalak.mycookbook.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{recipeId}") {
        fun passId(id: Int): String = "details/$id"
    }
}