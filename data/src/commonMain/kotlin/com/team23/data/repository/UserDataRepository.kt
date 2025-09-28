package com.team23.data.repository

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.models.UserDataModel
import com.team23.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class UserDataRepository(
    popoteLocalDataSource: PopoteLocalDataSource,
) : UserRepository {
    private val userDao = popoteLocalDataSource.userDao

    override fun getNickname(): Flow<String?> = userDao.getFirst().map { it?.name }

    override suspend fun setNickname(nickname: String) {
        val id = userDao.getFirst()
            .firstOrNull()
            ?.id ?: 0L
        userDao.insertOrReplace(UserDataModel(id = id, name = nickname))
    }
}
