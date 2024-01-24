package com.team23.neuracrsrecipes.viewmodel


import androidx.compose.runtime.mutableStateOf
import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.domain.recipe.usecase.DeleteRecipeUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.domain.recipe.usecase.SetRecipeBackToTempUseCase
import com.team23.neuracrsrecipes.extension.toReadableQuantity
import com.team23.neuracrsrecipes.extension.toUrlRecipeId
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.RecipeUiMapper
import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.neuracrsrecipes.model.uimodel.IngredientUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uistate.RecipeUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.getScopeName

class RecipeViewModel(
    private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val setRecipeBackToTempUseCase: SetRecipeBackToTempUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val recipeUiMapper: RecipeUiMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,

    ) {
	private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
	val uiState: StateFlow<RecipeUiState> = _uiState
	val currentServingsAmount = mutableStateOf(0)
	private lateinit var defaultIngredients: List<IngredientUiModel>

	fun getRecipe(sanitizedRecipeId: String?) {
		if (sanitizedRecipeId == null) {
			_uiState.value = RecipeUiState.Error(createErrorUiModel("The recipe link is invalid"))
		} else {
			viewModelScope.launch(Dispatchers.Main) {
				getFullRecipeByIdUseCase.invoke(sanitizedRecipeId.toUrlRecipeId())
					.onSuccess { fullRecipe ->
						val recipeUiModel = recipeUiMapper.toRecipeUiModel(fullRecipe)
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
							createErrorUiModel("${it.getScopeName()} ${it.message}")
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

	fun favoriteClick(recipe: RecipeUiModel) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipe.id)
			recomputeState(recipe)
			if (!recipe.isFavorite) {
				val result = snackbarHandler.showFavoriteMessage(recipe.title)
				if (result == SnackbarResultUiModel.ActionPerformed) {
					updateFavoriteUseCase.invoke(recipe.id)
					recomputeState(recipe)
				}
			}
		}
	}

	fun onLocalPhoneClick() {
		viewModelScope.launch(Dispatchers.IO) {
			snackbarHandler.showLocalPhoneMessage()
		}
	}

	fun onUpdateLocalRecipe(navigateAdd: () -> Unit) {
		viewModelScope.launch(Dispatchers.IO) {
			val recipe: RecipeUiModel = (_uiState.value as? RecipeUiState.Data)?.recipe ?: return@launch
			setRecipeBackToTempUseCase.invoke(recipeId = recipe.id)
			withContext(Dispatchers.Main) {
				navigateAdd()
			}
		}
	}

	fun onDeleteLocalRecipe(navigateHome: () -> Unit) {
		viewModelScope.launch(Dispatchers.IO) {
			val recipe: RecipeUiModel = (_uiState.value as? RecipeUiState.Data)?.recipe ?: return@launch
			val hasRecipeBeenDeleted = deleteRecipeUseCase.invoke(recipe.id)

			if (hasRecipeBeenDeleted) {
				withContext(Dispatchers.Main) {
					snackbarHandler.showRecipeHasBeenDeleted(recipe.title)
					navigateHome()
				}
			} else {
				snackbarHandler.showRecipeHasNotBeenDeleted(recipe.title)
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
							floatQuantityWithRatio.toReadableQuantity()
						})
					}
				)
			}

			_uiState.value = RecipeUiState.Data(recipeWithQuantitiesUpdated)
		}
	}

	private fun createErrorUiModel(message: String): ErrorUiModel = ErrorUiModel(
		message = message,
		// TODO HANDLE WEBSITE REDIRECTION
	) {}
}
