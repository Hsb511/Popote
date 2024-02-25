package com.team23.data.datasource

import com.team23.data.dao.FavoriteDao
import com.team23.data.dao.SummarizedRecipeDao
import data.AppDatabase


internal class NeuracrLocalDataSource(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQueries = database.appDatabaseQueries

    internal val summarizedRecipeDao = SummarizedRecipeDao(dbQueries)
    internal val favoriteDao = FavoriteDao(dbQueries)
}
