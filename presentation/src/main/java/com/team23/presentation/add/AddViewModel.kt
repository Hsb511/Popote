package com.team23.presentation.add

import androidx.lifecycle.ViewModel
import com.team23.data.mappers.FullRecipeMapper
import com.team23.domain.usecases.CreateNewRecipeUseCase
import com.team23.presentation.add.models.AddRecipeUiModel
import com.team23.presentation.recipe.mappers.RecipeMapper
import com.team23.presentation.recipe.models.RecipeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
	createNewRecipeUseCase: CreateNewRecipeUseCase,
	recipeMapper: RecipeMapper,
) : ViewModel() {
	private val _recipe = MutableStateFlow(recipeMapper.toRecipeUiModel(createNewRecipeUseCase.invoke()))

	val initialRecipe: AddRecipeUiModel = AddRecipeUiModel(
		recipe = _recipe.value,
		onTitleChange = { newTitle -> onTitleChange(newTitle) },
		onAuthorChange = { newAuthor -> onAuthorChange(newAuthor) },
	)

	private fun onTitleChange(newTitle: String) {
		_recipe.value = _recipe.value.copy(title = newTitle)
	}

	private fun onAuthorChange(newAuthor: String) {
		_recipe.value = _recipe.value.copy(author = newAuthor)
	}
}
