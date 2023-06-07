package com.team23.presentation.recipe

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetFullRecipeByIdUseCase
import com.team23.domain.usecases.UpdateFavoriteUseCase
import com.team23.presentation.common.handlers.SnackbarHandler
import com.team23.presentation.recipe.extensions.toUrlRecipeId
import com.team23.presentation.recipe.mappers.QuantityMapper
import com.team23.presentation.recipe.mappers.RecipeMapper
import com.team23.presentation.recipe.models.IngredientUiModel
import com.team23.presentation.recipe.models.RecipeUiModel
import com.team23.presentation.recipe.models.RecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
	private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
	private val updateFavoriteUseCase: UpdateFavoriteUseCase,
	private val recipeMapper: RecipeMapper,
	private val quantityMapper: QuantityMapper,
) : ViewModel() {
	private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
	val uiState: StateFlow<RecipeUiState> = _uiState
	val currentServingsAmount = mutableStateOf(0)
	private lateinit var defaultIngredients: List<IngredientUiModel>

	fun getRecipe(sanitizedRecipeId: String?) {
		if (sanitizedRecipeId == null) {
			_uiState.value = RecipeUiState.Error("The recipe link is invalid")
		} else {
			viewModelScope.launch(Dispatchers.Main) {
				getFullRecipeByIdUseCase.invoke(sanitizedRecipeId.toUrlRecipeId())
					.onSuccess { fullRecipe ->
						val recipeUiModel = recipeMapper.toRecipeUiModel(fullRecipe)
						if (currentServingsAmount.value == 0) {
							currentServingsAmount.value = recipeUiModel.defaultServingsAmount
						}
						if (_uiState.value !is RecipeUiState.Data) {
							_uiState.value = RecipeUiState.Data(recipeUiModel)
						}
						if (!this@RecipeViewModel::defaultIngredients.isInitialized) {
							defaultIngredients = recipeUiModel.ingredients
						}
					}
					.onFailure {
						_uiState.value = RecipeUiState.Error(
							"${it.javaClass.simpleName} ${it.localizedMessage}"
						)
					}
			}
		}
	}

	fun addOneService() {
		if (currentServingsAmount.value < 999) {
			currentServingsAmount.value++
		}
		updateRecipeData()
	}

	fun subtractOneService() {
		if (currentServingsAmount.value > 1) {
			currentServingsAmount.value--
		}
		updateRecipeData()
	}

	fun updateRecipeData(newServingsAmount: String) {
		newServingsAmount.toIntOrNull()?.let { newServingsAmountInt ->
			currentServingsAmount.value = when {
				newServingsAmountInt < 1 -> 1
				newServingsAmountInt > 999 -> 999
				else -> newServingsAmountInt
			}
			updateRecipeData()
		}
	}

	fun favoriteClick(recipe: RecipeUiModel, snackbarHostState: SnackbarHostState, context: Context) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipe.id)
			recomputeState(recipe)
			if (!recipe.isFavorite) {
				val result = SnackbarHandler(snackbarHostState, context).showFavoriteSnackbar(recipe.title)
				if (result == SnackbarResult.ActionPerformed) {
					updateFavoriteUseCase.invoke(recipe.id)
					recomputeState(recipe)
				}
			}
		}
	}

	private fun recomputeState(recipe: RecipeUiModel) {
		val currentState = _uiState.value
		if (currentState is RecipeUiState.Data) {
			_uiState.value = RecipeUiState.Data(recipe.copy(isFavorite = !recipe.isFavorite))
		}
	}

	private fun updateRecipeData() {
		val currentRecipeState = _uiState.value
		if (currentRecipeState is RecipeUiState.Data) {
			val recipeWithQuantitiesUpdated = with(currentRecipeState.recipe) {
				copy(
					ingredients = ingredients.map { ingredientUiModel ->
						val defaultIngredient = defaultIngredients.firstOrNull { it.label == ingredientUiModel.label }
						ingredientUiModel.copy(quantity = defaultIngredient?.quantity?.let { quantity ->
							val floatQuantity = quantity.replace(",", ".").toFloat()
							val floatQuantityWithRatio =
								floatQuantity * currentServingsAmount.value / defaultServingsAmount
							quantityMapper.toString(floatQuantityWithRatio)
						})
					}
				)
			}

			viewModelScope.launch(Dispatchers.IO) {
				_uiState.emit(RecipeUiState.Data(recipeWithQuantitiesUpdated))
			}
		}
	}
}
