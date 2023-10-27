package com.team23.data.repositories

import com.team23.data.daos.UserDao
import com.team23.data.models.UserDataModel
import com.team23.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
	private val userDao: UserDao,
) : UserRepository {
	override suspend fun getNickname(): String? =
		userDao.loadAll()
			.map { it.name }
			.firstOrNull()

	override suspend fun setNickname(nickname: String) {
		val id = userDao.loadAll()
			.map { it.id }
			.firstOrNull()
			?: 0L
		userDao.insertOrReplace(UserDataModel(id = id, name = nickname))
	}
}
