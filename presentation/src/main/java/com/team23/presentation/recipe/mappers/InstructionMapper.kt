package com.team23.presentation.recipe.mappers

import com.team23.domain.models.InstructionDomainModel
import com.team23.presentation.recipe.models.InstructionUiModel
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
