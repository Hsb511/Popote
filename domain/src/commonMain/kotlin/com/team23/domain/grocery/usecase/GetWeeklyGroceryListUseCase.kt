package com.team23.domain.grocery.usecase

import com.team23.domain.grocery.model.GroceryDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetWeeklyGroceryListUseCase {

    fun invoke(): Flow<GroceryDomainModel> {
        return flowOf(GroceryDomainModel())
    }
}
