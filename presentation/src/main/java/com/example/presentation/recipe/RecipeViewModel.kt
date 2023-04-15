package com.example.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetFullRecipeByIdUseCase
import com.example.presentation.recipe.extensions.toUrlRecipeId
import com.example.presentation.recipe.mappers.RecipeMapper
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
) : ViewModel() {
	private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
	val uiState: StateFlow<RecipeUiState> = _uiState

	fun getRecipe(sanitizedRecipeId: String?) {
		if (sanitizedRecipeId == null) {
			_uiState.value = RecipeUiState.Error
		} else {
			viewModelScope.launch(Dispatchers.IO) {
				getFullRecipeByIdUseCase.invoke(sanitizedRecipeId.toUrlRecipeId())
					.onSuccess { fullRecipe ->
						_uiState.value = RecipeUiState.Data(
							recipe = recipeMapper.toRecipeUiModel(fullRecipe)
						)
					}
					.onFailure {
						_uiState.value = RecipeUiState.Error
					}
			}
		}
	}
}
