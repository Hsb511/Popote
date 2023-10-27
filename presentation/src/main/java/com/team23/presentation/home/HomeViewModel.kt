package com.team23.presentation.home

import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.recipe.usecase.GetAllSummarizedRecipesUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.presentation.common.handlers.SnackbarHandler
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import com.team23.presentation.home.models.HomeUiState
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

	var snackbarHandler: SnackbarHandler? = null

	init {
		viewModelScope.launch(Dispatchers.IO) {
			viewModelScope.launch(Dispatchers.IO) {
				snackbarHandler?.showStartLoading()
			}
			getAllSummarizedRecipesUseCase.invoke()
				.onFailure {
					withContext(Dispatchers.Main) {
						_uiState.value = HomeUiState.Error(message = "${it.javaClass.simpleName} ${it.localizedMessage}")
					}
				}.onSuccess { data ->
					val recipes = data.first
					val newRecipesCount = data.second
					viewModelScope.launch(Dispatchers.IO) {
						snackbarHandler?.showLoadingRecipe(newRecipesCount)
					}

					withContext(Dispatchers.Main) {
						_uiState.value = HomeUiState.Data(recipes = recipes.map { summarizedRecipeMapper.toUiModel(it) })
					}
					recipes.forEach { recipe ->
						getFullRecipeByIdUseCase.invoke(recipe.id)
					}
					if (newRecipesCount != 0) {
						snackbarHandler?.showLoadingEnded()
					}
				}
		}
	}

	fun favoriteClick(recipe: SummarizedRecipeUiModel) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipe.id)
			recomputeState(recipe.id)
			if (!recipe.isFavorite) {
				val result = snackbarHandler?.showFavoriteSnackbar(recipe.title)
				if (result == SnackbarResult.ActionPerformed) {
					updateFavoriteUseCase.invoke(recipe.id)
					recomputeState(recipe.id)
				}
			}
		}
	}

	fun onLocalPhoneClick() {
		viewModelScope.launch(Dispatchers.IO) {
			snackbarHandler?.showLocalPhoneClick()
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
			println("HUGO - recomputeState ${_uiState.value}")
		}
	}
}
