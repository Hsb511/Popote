package com.team23.data.dao

import com.team23.data.models.InstructionDataModel
import data.AppDatabaseQueries

internal class InstructionDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(instructionDataModels: List<InstructionDataModel>) {
        instructionDataModels
            .map { it.toDbModel().also { a -> println("HUGO - $it -> $a") } }
            .forEach { instruction ->
                dbQueries.insertInstruction(
                    recipeId = instruction.recipeId,
                    label = instruction.label,
                    order_ = instruction.order_,
                )
            }
    }

    fun deleteAllByRecipeId(recipeId: String) {
        dbQueries.deleteTagByRecipeId(recipeId)
    }

    fun getAllByRecipeId(recipeId: String): List<InstructionDataModel> =
        dbQueries.selectInstructionsByRecipeId(recipeId).executeAsList().map { it.toDataModel() }

    private fun InstructionDataModel.toDbModel() = data.InstructionDataModel(
        id = id, recipeId = recipeId, label = label, order_ = order.toLong()
    )

    private fun data.InstructionDataModel.toDataModel() = InstructionDataModel(
        id = id, recipeId = recipeId, label = label, order = order_.toInt()
    )
}
