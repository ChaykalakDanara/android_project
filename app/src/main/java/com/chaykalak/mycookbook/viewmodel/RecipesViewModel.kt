package com.chaykalak.mycookbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaykalak.mycookbook.data.Recipe
import com.chaykalak.mycookbook.data.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class RecipesUiState {
    object Loading : RecipesUiState()
    data class Success(val recipes: List<Recipe>) : RecipesUiState()
    data class Error(val message: String) : RecipesUiState()
}

class RecipesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<RecipesUiState>(RecipesUiState.Loading)
    val uiState: StateFlow<RecipesUiState> = _uiState.asStateFlow()

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _uiState.value = RecipesUiState.Loading
            try {
                val recipes = RecipesRepository.getRecipes()
                _uiState.value = RecipesUiState.Success(recipes)
            } catch (e: Exception) {
                _uiState.value = RecipesUiState.Error("Ошибка загрузки: ${e.message}")
            }
        }
    }

    fun selectRecipe(recipeId: Int) {
        if (_uiState.value is RecipesUiState.Success) {
            val recipes = (_uiState.value as RecipesUiState.Success).recipes
            _selectedRecipe.value = recipes.find { it.id == recipeId }
        }
    }

    fun clearSelectedRecipe() {
        _selectedRecipe.value = null
    }

    fun refreshData() {
        loadRecipes()
    }
}