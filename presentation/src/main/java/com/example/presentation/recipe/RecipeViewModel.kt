package com.example.presentation.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetFullRecipeByIdUseCase
import com.example.presentation.recipe.extensions.toUrlRecipeId
import com.example.presentation.recipe.mappers.QuantityMapper
import com.example.presentation.recipe.mappers.RecipeMapper
import com.example.presentation.recipe.models.IngredientUiModel
import com.example.presentation.recipe.models.RecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
	private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
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
			viewModelScope.launch(Dispatchers.IO) {
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
		newServingsAmount.toIntOrNull()?.let { newServingsAmount ->
			currentServingsAmount.value = when {
				newServingsAmount < 1 -> 1
				newServingsAmount > 999 -> 999
				else -> newServingsAmount
			}
			updateRecipeData()
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
