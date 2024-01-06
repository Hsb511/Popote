package com.team23.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetUserNicknameUseCase(
    // private val userRepository: UserRepository,
) {
    fun invoke(): Flow<String?> = flowOf("Hugo")// userRepository.getNickname()
}
