package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.team23.data.models.FavoriteDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class FavoriteDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun isStored(recipeId: String): Boolean = dbQueries.isFavoriteStored(recipeId).executeAsOne()

    fun insertOrReplace(favoriteDataModel: FavoriteDataModel) {
        dbQueries.insertFavorite(favoriteDataModel.recipeId)
    }

    fun delete(recipeId: String) {
        dbQueries.deleteFavorite(recipeId)
    }

    fun getAllFavorites(): Flow<List<String>> = dbQueries.selectAllFavorites(toDataModel())
        .asFlow()
        .mapToList(Dispatchers.IO)

    private fun toDataModel() = { _: Long, recipeId: String ->
        recipeId
    }
}
