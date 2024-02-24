package com.team23.data.datasource

import com.team23.data.models.SummarizedRecipeDataModel
import data.AppDatabase


internal class NeuracrLocalDataSource(databaseDriverFactory: DatabaseDriverFactory) {
        private val database = AppDatabase(databaseDriverFactory.createDriver())
        private val dbQuery = database.appDatabaseQueries

        internal fun getAllSummarizedRecipes(): List<SummarizedRecipeDataModel> {
            //dbQuery.
            return emptyList()
        }
    }
