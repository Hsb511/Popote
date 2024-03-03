package com.team23.data.dao

import com.team23.data.models.IngredientDataModel
import data.AppDatabaseQueries

internal class IngredientDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(vararg ingredientDataModel: IngredientDataModel) {
        ingredientDataModel
            .toList()
            .map { it.toDbModel() }
            .forEach { dbQueries.insertIngredient(it) }
    }

    fun deleteAllByRecipeId(recipeId: String) {
        dbQueries.deleteTagByRecipeId(recipeId)
    }

    fun getAllByRecipeId(recipeId: String): List<IngredientDataModel> =
        dbQueries.selectIngredientsByRecipeId(recipeId).executeAsList().map { it.toDataModel() }

    private fun IngredientDataModel.toDbModel() = data.IngredientDataModel(
        id = id, recipeId = recipeId, label = label, quantity = quantity, unit = unit,
    )

    private fun data.IngredientDataModel.toDataModel() = IngredientDataModel(
        id = id, recipeId = recipeId, label = label, quantity = quantity, unit = unit,
    )
}
