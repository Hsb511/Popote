package com.example.presentation.home.models

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class Data(val recipes: List<HomeRecipeUiModel>) : HomeUiState()
}
