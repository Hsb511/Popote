package com.team23.data.dao

import com.team23.data.models.InstructionDataModel
import data.AppDatabaseQueries

internal class InstructionDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(vararg instructionDataModel: InstructionDataModel) {
        instructionDataModel
            .toList()
            .map { it.toDbModel() }
            .forEach { dbQueries.insertInstruction(it) }
    }

    fun deleteAllByRecipeId(recipeId: String) {
        dbQueries.deleteTagByRecipeId(recipeId)
    }

    private fun InstructionDataModel.toDbModel() = data.InstructionDataModel(
        id = id, recipeId = recipeId, label = label, order_ = order.toLong()
    )
}
