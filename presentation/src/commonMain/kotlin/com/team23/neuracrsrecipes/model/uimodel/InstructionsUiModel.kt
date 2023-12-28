package com.team23.neuracrsrecipes.model.uimodel

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

data class InstructionUiModel(
	val order: Int,
	val label: String,
)
