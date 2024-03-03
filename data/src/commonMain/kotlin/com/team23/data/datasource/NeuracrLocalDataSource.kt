package com.team23.data.datasource

import com.team23.data.dao.FavoriteDao
import com.team23.data.dao.PreferenceDao
import com.team23.data.dao.SummarizedRecipeDao
import com.team23.data.dao.TagDao
import com.team23.data.dao.UserDao
import data.AppDatabase


internal class NeuracrLocalDataSource(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQueries = database.appDatabaseQueries

    internal val favoriteDao = FavoriteDao(dbQueries)
    internal val preferenceDao = PreferenceDao(dbQueries)
    internal val summarizedRecipeDao = SummarizedRecipeDao(dbQueries)
    internal val tagDao = TagDao(dbQueries)
    internal val userDao = UserDao(dbQueries)
}
