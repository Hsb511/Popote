package com.team23.presentation.home

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAllSummarizedRecipesUseCase
import com.team23.domain.usecases.GetFullRecipeByIdUseCase
import com.team23.domain.usecases.UpdateFavoriteUseCase
import com.team23.presentation.common.handlers.SnackbarHandler
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import com.team23.presentation.home.models.HomeUiState
import com.team23.presentation.home.models.SummarizedRecipeUiModel
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
	private val updateFavoriteUseCase: UpdateFavoriteUseCase,
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

	fun favoriteClick(recipe: SummarizedRecipeUiModel, snackbarHostState: SnackbarHostState, context: Context) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipe.id)
			recomputeState(recipe.id)
			if (!recipe.isFavorite) {
				val result = SnackbarHandler(snackbarHostState, context).showFavoriteSnackbar(recipe.title)
				if (result == SnackbarResult.ActionPerformed) {
					updateFavoriteUseCase.invoke(recipe.id)
					recomputeState(recipe.id)
				}
			}
		}
	}

	private fun recomputeState(recipeId: String) {
		val currentState = _uiState.value
		if (currentState is HomeUiState.Data) {
			val newRecipes = currentState.recipes.toMutableList()
			val recipe = newRecipes.first { recipe -> recipe.id == recipeId }
			val recipeIndex = newRecipes.indexOf(recipe)
			newRecipes.remove(recipe)
			newRecipes.add(recipeIndex, recipe.copy(isFavorite = !recipe.isFavorite))
			_uiState.value = HomeUiState.Data(newRecipes)
		}
	}
}
