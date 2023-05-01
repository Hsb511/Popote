package com.example.presentation.recipe.mappers

import com.example.domain.models.InstructionDomainModel
import com.example.presentation.recipe.models.InstructionUiModel
import javax.inject.Inject

class InstructionMapper @Inject constructor() {
	fun toInstructionUiModels(instructions: List<InstructionDomainModel>): List<InstructionUiModel> =
		instructions.map { instruction ->
			InstructionUiModel(
				order = instruction.order,
				label = instruction.label,
			)
		}
}
