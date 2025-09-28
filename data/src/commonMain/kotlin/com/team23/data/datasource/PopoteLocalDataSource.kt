package com.team23.data.datasource

import com.team23.data.dao.FavoriteDao
import com.team23.data.dao.IngredientDao
import com.team23.data.dao.InstructionDao
import com.team23.data.dao.PreferenceDao
import com.team23.data.dao.BaseRecipeDao
import com.team23.data.dao.SummarizedRecipeDao
import com.team23.data.dao.TagDao
import com.team23.data.dao.UserDao
import data.AppDatabase


internal class PopoteLocalDataSource(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQueries = database.appDatabaseQueries

    internal val favoriteDao = FavoriteDao(dbQueries)
    internal val ingredientDao = IngredientDao(dbQueries)
    internal val instructionDao = InstructionDao(dbQueries)
    internal val preferenceDao = PreferenceDao(dbQueries)
    internal val baseRecipeDao = BaseRecipeDao(dbQueries)
    internal val summarizedRecipeDao = SummarizedRecipeDao(dbQueries)
    internal val tagDao = TagDao(dbQueries)
    internal val userDao = UserDao(dbQueries)
}
