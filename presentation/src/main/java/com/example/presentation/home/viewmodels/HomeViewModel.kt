package com.example.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetAllSummarizedRecipesUseCase
import com.example.presentation.home.mappers.HomeRecipeMapper
import com.example.presentation.home.models.HomeRecipeUiModel
import com.example.presentation.home.models.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSummarizedRecipesUseCase: GetAllSummarizedRecipesUseCase,
    private val homeRecipeMapper: HomeRecipeMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllSummarizedRecipesUseCase.invoke().onFailure {
                _uiState.value = HomeUiState.Error
            }.onSuccess { recipes ->
                _uiState.value = HomeUiState.Data(recipes = recipes.map { homeRecipeMapper.toUiModel(it) })
            }
        }
    }
}
