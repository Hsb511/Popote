package com.example.data.mappers

import com.example.data.models.InstructionDataModel
import com.example.domain.models.InstructionDomainModel
import javax.inject.Inject

class InstructionMapper @Inject constructor() {
	fun toInstructionListDomainModel(instructionDataModels: List<InstructionDataModel>): List<InstructionDomainModel> =
		instructionDataModels.map(::toInstructionDomainModel)

	private fun toInstructionDomainModel(
		instructionDataModel: InstructionDataModel
	): InstructionDomainModel =
		InstructionDomainModel(
			order = instructionDataModel.order + 1,
			label = instructionDataModel.label,
		)
}
