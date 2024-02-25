package com.team23.domain.user.usecase

import com.team23.domain.user.repository.UserRepository

class SetUserNicknameUseCase(
    private val userRepository: UserRepository,
) {
    suspend fun invoke(nickname: String) {
        userRepository.setNickname(nickname)
    }
}
