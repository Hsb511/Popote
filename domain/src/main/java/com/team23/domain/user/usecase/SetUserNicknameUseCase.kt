package com.team23.domain.user.usecase

import com.team23.domain.user.repository.UserRepository
import javax.inject.Inject

class SetUserNicknameUseCase @Inject constructor(
	private val userRepository: UserRepository,
){
	suspend fun invoke(nickname: String) {
		userRepository.setNickname(nickname)
	}
}
