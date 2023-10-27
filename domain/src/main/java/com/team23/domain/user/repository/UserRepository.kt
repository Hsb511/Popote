package com.team23.domain.user.repository

interface UserRepository {
	suspend fun getNickname(): String?
	suspend fun setNickname(nickname: String)
}
