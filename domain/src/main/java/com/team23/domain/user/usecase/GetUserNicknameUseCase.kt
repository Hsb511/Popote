package com.team23.domain.user.usecase

import com.team23.domain.user.repository.UserRepository
import javax.inject.Inject

class GetUserNicknameUseCase @Inject constructor(
	private val userRepository: UserRepository,
) {
	suspend fun invoke(): String? = userRepository.getNickname()
}
