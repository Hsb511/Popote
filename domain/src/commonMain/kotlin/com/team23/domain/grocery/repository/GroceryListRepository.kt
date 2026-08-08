package com.team23.domain.grocery.repository

import com.team23.domain.grocery.model.GroceryDomainModel
import kotlinx.coroutines.flow.Flow

interface GroceryListRepository {
    suspend fun updateGroceryList(recipeId: String): Boolean
    fun getGroceryList(): Flow<GroceryDomainModel>
    suspend fun clearAllGroceryList()
}
