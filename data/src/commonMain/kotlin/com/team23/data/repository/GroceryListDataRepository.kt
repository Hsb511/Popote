package com.team23.data.repository

import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.domain.grocery.repository.GroceryListRepository
import kotlinx.coroutines.flow.Flow

class GroceryListDataRepository: GroceryListRepository {

    override suspend fun updateGroceryList(recipeId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGroceryList(): Flow<GroceryDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllGroceryList() {
        TODO("Not yet implemented")
    }
}
