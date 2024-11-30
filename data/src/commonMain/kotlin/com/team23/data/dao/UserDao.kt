package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.team23.data.models.UserDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class UserDao(
    private val dbQueries: AppDatabaseQueries,
) {
    suspend fun insertOrReplace(vararg user: UserDataModel) {
        user.forEach {
            dbQueries.deleteUser(it.id)
            dbQueries.insertUser(toDbModel(it))
        }
    }

    fun getFirst(): Flow<UserDataModel?> = dbQueries.selectUser(toDataModel())
        .asFlow()
        .mapToOneOrNull(Dispatchers.IO)

    private fun toDataModel() = { id: Long, name: String ->
        UserDataModel(id, name)
    }

    private fun toDbModel(user: UserDataModel) = data.UserDataModel(user.id, user.name)
}
