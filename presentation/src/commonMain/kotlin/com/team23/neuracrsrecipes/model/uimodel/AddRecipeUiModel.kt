package com.team23.neuracrsrecipes.model.uimodel

import com.team23.neuracrsrecipes.model.property.ImageProperty

data class AddRecipeUiModel(
    val recipe: RecipeUiModel,
    val onTitleChange: (String) -> Unit = {},
    val onAuthorChange: (String) -> Unit = {},
    val onAddTag: (String) -> Unit = {},
    val onRemoveTag: (String) -> Unit = {},
    val onAddImage: (ImageProperty) -> Unit = {},
    val onAddIngredient: () -> Unit = {},
    val onDeleteIngredient: (Int) -> Unit = {},
    val onUpdateIngredient: (IngredientUiModel, Int) -> Unit = { _, _ -> },
    val onServingsAmountChange: (String) -> Unit = {},
    val onAddOneServing: () -> Unit = {},
    val onSubtractOneServing: () -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onAddInstruction: () -> Unit = {},
    val onDeleteInstruction: (InstructionUiModel) -> Unit = {},
    val onUpdateInstruction: (InstructionUiModel) -> Unit = {},
    val onConclusionChange: (String) -> Unit = {},
    val onLaunchSettings: () -> Unit = {},
)
