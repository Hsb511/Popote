package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.team23.data.models.GroceryListDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class GroceryListDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun isStored(recipeId: String): Boolean =
        dbQueries.isGroceryListItemStored(recipeId).executeAsOne()

    fun insert(groceryListDataModel: GroceryListDataModel) {
        dbQueries.insertGroceryListItem(
            recipeId = groceryListDataModel.recipeId,
            servingsAmount = groceryListDataModel.servingsAmount.toLong(),
        )
    }

    fun updateServings(recipeId: String, servingsAmount: Int) {
        dbQueries.updateGroceryListItemServings(
            servingsAmount = servingsAmount.toLong(),
            recipeId = recipeId,
        )
    }

    fun delete(recipeId: String) {
        dbQueries.deleteGroceryListItem(recipeId)
    }

    fun getAll(): Flow<List<GroceryListDataModel>> =
        dbQueries.selectAllGroceryListItems(toDataModel())
            .asFlow()
            .mapToList(Dispatchers.IO)

    fun deleteAll() {
        dbQueries.deleteAllGroceryListItems()
    }

    private fun toDataModel() = { recipeId: String, servingsAmount: Long ->
        GroceryListDataModel(recipeId = recipeId, servingsAmount = servingsAmount.toInt())
    }
}
