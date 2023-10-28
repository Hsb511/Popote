package com.team23.domain.user.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
	fun getNickname(): Flow<String?>
	suspend fun setNickname(nickname: String)
}
