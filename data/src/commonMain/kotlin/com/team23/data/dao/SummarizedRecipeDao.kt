package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.team23.data.models.SummarizedRecipeDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class SummarizedRecipeDao(
    private val dbQueries: AppDatabaseQueries,
) {

    internal fun getAll(): List<SummarizedRecipeDataModel> =
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

    internal fun searchBaseRecipeByTitle(search: String): Flow<List<SummarizedRecipeDataModel>> =
        dbQueries.selectByTitle(search, toDataModel())
            .asFlow()
            .mapToList(Dispatchers.IO)

    internal fun deleteByRecipeId(recipeId: String) {
        dbQueries.deleteSummarizeRecipe(recipeId)
    }

    private fun toDataModel() = { href: String, imgSrc: String, title: String ->
        SummarizedRecipeDataModel(href, imgSrc, title)
    }
}
