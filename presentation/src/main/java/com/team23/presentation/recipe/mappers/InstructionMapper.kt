package com.team23.presentation.recipe.mappers

import com.team23.domain.models.InstructionDomainModel
import com.team23.presentation.recipe.models.InstructionUiModel
import javax.inject.Inject

class InstructionMapper @Inject constructor() {
	fun toInstructionUiModels(instructions: List<InstructionDomainModel>): List<InstructionUiModel> =
		instructions.map {
			with(it) {
				InstructionUiModel(order = order, label = label)
			}
		}

	fun toInstructionDomainModels(instructions: List<InstructionUiModel>): List<InstructionDomainModel> =
		instructions.map {
			with(it) {
				InstructionDomainModel(order = order, label = label)
			}
		}
}
