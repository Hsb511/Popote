package com.team23.data.dao

import com.team23.data.extensions.toBoolean
import com.team23.data.extensions.toLong
import com.team23.data.models.BaseRecipeDataModel
import data.AppDatabaseQueries

internal class BaseRecipeDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun findBaseRecipeById(recipeId: String): BaseRecipeDataModel? =
        dbQueries.selectBaseRecipeByHref(recipeId).executeAsOneOrNull()?.toDataModel()

    fun insertOrReplace(vararg baseRecipeDataModel: BaseRecipeDataModel) {
        baseRecipeDataModel
            .toList()
            .map { it.toDbModel() }
            .forEach { dbQueries.insertBaseRecipe(it) }
    }

    fun deleteByRecipeId(recipeId: String) {
        dbQueries.deleteBaseRecipeByHref(recipeId)
    }

    fun getAllSubtitles(): List<String> = dbQueries.selectAllBaseRecipeSubtitles().executeAsList()

    private fun BaseRecipeDataModel.toDbModel() = data.BaseRecipeDataModel(
        href = href,
        imgSrc = imgSrc,
        title = title,
        subTitle = subTitle,
        servingsAmount = servingsAmount.toLong(),
        instructionTitle = instructionTitle,
        lastTitle = lastTitle,
        isSourceLocal = isSourceLocal.toLong(),
        isTemporary = isTemporary.toLong(),
    )

    private fun data.BaseRecipeDataModel.toDataModel() = BaseRecipeDataModel(
        href = href,
        imgSrc = imgSrc,
        title = title,
        subTitle = subTitle,
        servingsAmount = servingsAmount.toInt(),
        instructionTitle = instructionTitle,
        lastTitle = lastTitle,
        isSourceLocal = isSourceLocal.toBoolean(),
        isTemporary = isTemporary.toBoolean(),
    )
}
