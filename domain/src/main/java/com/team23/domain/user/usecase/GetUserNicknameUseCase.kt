package com.team23.domain.user.usecase

import com.team23.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserNicknameUseCase @Inject constructor(
	private val userRepository: UserRepository,
) {
	fun invoke(): Flow<String?> = userRepository.getNickname()
}
