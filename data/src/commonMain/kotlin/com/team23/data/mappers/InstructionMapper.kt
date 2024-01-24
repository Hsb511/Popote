package com.team23.data.mappers

import com.team23.data.models.InstructionDataModel
import com.team23.domain.recipe.model.InstructionDomainModel

class InstructionMapper {
	fun toInstructionListDomainModel(instructionDataModels: List<InstructionDataModel>): List<InstructionDomainModel> =
		instructionDataModels.map(::toInstructionDomainModel)

	fun toInstructionListDataModel(recipeId: String, instructions: List<InstructionDomainModel>): List<InstructionDataModel> =
		instructions.map { instruction -> toInstructionDataModel(recipeId, instruction) }

	private fun toInstructionDomainModel(instructionDataModel: InstructionDataModel): InstructionDomainModel =
		InstructionDomainModel(
			order = instructionDataModel.order + 1,
			label = instructionDataModel.label,
		)

	private fun toInstructionDataModel(recipeId: String, instruction: InstructionDomainModel) = InstructionDataModel(
		recipeId = recipeId,
		order = instruction.order - 1,
		label = instruction.label,
	)
}
