package com.team23.presentation.add.models

import com.team23.presentation.recipe.models.RecipeUiModel

data class AddRecipeUiModel(
	val recipe: RecipeUiModel,
	val onTitleChange: (String) -> Unit,
	val onAuthorChange: (String) -> Unit,
	val onAddTag: () -> Unit,
)
