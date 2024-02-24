package com.team23.data.datasource

import com.team23.data.models.SummarizedRecipeDataModel
import data.AppDatabase


internal class NeuracrLocalDataSource(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllSummarizedRecipes(): List<SummarizedRecipeDataModel> =
        dbQuery.selectAllSummarizedRecipes { href, imgSrc, title ->
            SummarizedRecipeDataModel(href, imgSrc, title)
        }.executeAsList()

    internal fun getCount(): Long = dbQuery.countAllSummarizedRecipes().executeAsOne()

    internal fun insertAll(vararg summarizedRecipeDataModel: SummarizedRecipeDataModel) {
        summarizedRecipeDataModel.forEach { summarizedRecipe ->
            with(summarizedRecipe) {
                dbQuery.insertSummarizedRecipe(
                    href = href, imgSrc = imgSrc, title = title,
                )
            }
        }
    }
}
