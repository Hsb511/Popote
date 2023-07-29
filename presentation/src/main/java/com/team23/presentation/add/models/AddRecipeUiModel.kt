package com.team23.presentation.add.models

import com.team23.presentation.recipe.models.RecipeUiModel

data class AddRecipeUiModel(
	val recipe: RecipeUiModel,
	val onTitleChange: (String) -> Unit,
	val onAuthorChange: (String) -> Unit,
	val onAddTag: (String) -> Unit,
	val onRemoveTag: (String) -> Unit,
	val onAddIngredient: (String) -> Unit,
	val onServingsAmountChange: (String) -> Unit,
	val onAddOneServing: () -> Unit,
	val onSubtractOneServing: () -> Unit,
	val onDescriptionChange: (String) -> Unit,
	val onConclusionChange: (String) -> Unit,

)