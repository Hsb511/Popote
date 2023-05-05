package com.team23.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAllSummarizedRecipesUseCase
import com.team23.domain.usecases.GetFullRecipeByIdUseCase
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import com.team23.presentation.home.models.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSummarizedRecipesUseCase: GetAllSummarizedRecipesUseCase,
    private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllSummarizedRecipesUseCase.invoke().onFailure {
                _uiState.value = HomeUiState.Error(message = "${it.javaClass.simpleName} ${it.localizedMessage}")
            }.onSuccess { recipes ->
                _uiState.value = HomeUiState.Data(recipes = recipes.map { summarizedRecipeMapper.toUiModel(it) })
                recipes.forEach { recipe ->
                    getFullRecipeByIdUseCase.invoke(recipe.id)
                }
            }
        }
    }
}
