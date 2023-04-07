package com.example.presentation.home.models

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Error : HomeUiState()
    data class Data(val recipes: List<HomeRecipeUiModel>) : HomeUiState()
}
