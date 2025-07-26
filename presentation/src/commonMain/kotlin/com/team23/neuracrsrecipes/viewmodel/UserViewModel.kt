package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.user.usecase.GetUserNicknameUseCase
import com.team23.domain.user.usecase.SetUserNicknameUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
	getUserNicknameUseCase: GetUserNicknameUseCase,
	// private val getExistingNicknamesUseCase: GetExistingNicknamesUseCase,
	private val setUserNicknameUseCase: SetUserNicknameUseCase,
	private val viewModelScope: CoroutineScope,
) {
	val nickname: StateFlow<String?> = getUserNicknameUseCase.invoke()
		.stateIn(viewModelScope, SharingStarted.Eagerly, null)

	fun onChangeLocalNickname(nickname: String) {
		viewModelScope.launch(Dispatchers.IO) {
			setUserNicknameUseCase.invoke(nickname)
		}
	}
}
