package com.team23.domain.grocery.repository

import com.team23.domain.grocery.model.GroceryDomainModel
import kotlinx.coroutines.flow.Flow

interface GroceryListRepository {
    suspend fun toggleInGroceryList(recipeId: String): Boolean
    suspend fun updateServings(recipeId: String, servingsAmount: Int)
    fun getGroceryList(): Flow<GroceryDomainModel>
    suspend fun clearAllGroceryList()
}
