package com.team23.data.mappers

import com.team23.data.models.InstructionDataModel
import com.team23.domain.models.InstructionDomainModel
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
