package com.team23.presentation.recipe.models

sealed class InstructionsUiModel(
	open val instructions: List<InstructionUiModel>,
) {
	data class FromRecipeScreen(override val instructions: List<InstructionUiModel>): InstructionsUiModel(instructions)
	data class FromAddScreen(
		override val instructions: List<InstructionUiModel>,
		val onAddInstruction: () -> Unit,
		val onUpdateInstruction: (InstructionUiModel) -> Unit,
		val onDeleteInstruction: (InstructionUiModel) -> Unit,
	): InstructionsUiModel(instructions)
}
