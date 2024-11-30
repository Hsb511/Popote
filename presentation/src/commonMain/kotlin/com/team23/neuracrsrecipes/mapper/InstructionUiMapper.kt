package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.InstructionDomainModel
import com.team23.neuracrsrecipes.model.uimodel.InstructionUiModel

class InstructionUiMapper {
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
