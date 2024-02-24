package com.team23.data.dao

import com.team23.data.models.SummarizedRecipeDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SummarizedRecipeDao(
    private val dbQueries: AppDatabaseQueries,
) {

    internal fun getAllSummarizedRecipes(): List<SummarizedRecipeDataModel> =
        dbQueries.selectAllSummarizedRecipes(toDataModel()).executeAsList()

    internal fun getCount(): Long = dbQueries.countAllSummarizedRecipes().executeAsOne()

    internal fun insertAll(vararg summarizedRecipeDataModel: SummarizedRecipeDataModel) {
        summarizedRecipeDataModel.forEach { summarizedRecipe ->
            with(summarizedRecipe) {
                dbQueries.insertSummarizedRecipe(
                    href = href, imgSrc = imgSrc, title = title,
                )
            }
        }
    }

    internal fun searchBaseRecipeByTitle(search: String): Flow<List<SummarizedRecipeDataModel>> = flow {
        emit(dbQueries.selectByTitle(search, toDataModel()).executeAsList())
    }

    internal fun deleteByRecipeId(recipeId: String) {
        dbQueries.deleteSummarizeRecipe(recipeId)
    }

    private fun toDataModel() = { href: String, imgSrc: String, title: String ->
        SummarizedRecipeDataModel(href, imgSrc, title)
    }
}