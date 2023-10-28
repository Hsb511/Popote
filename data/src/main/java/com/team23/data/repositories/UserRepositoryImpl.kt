package com.team23.data.repositories

import com.team23.data.daos.UserDao
import com.team23.data.models.UserDataModel
import com.team23.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
	private val userDao: UserDao,
) : UserRepository {
	override fun getNickname(): Flow<String?> =
		userDao.getFirst()
			.map { it?.name }

	override suspend fun setNickname(nickname: String) {
		val id = userDao.getFirst()
			.firstOrNull()
			?.id ?: 0L
		userDao.insertOrReplace(UserDataModel(id = id, name = nickname))
	}
}
