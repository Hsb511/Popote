package com.team23.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.user.usecase.GetExistingNicknamesUseCase
import com.team23.domain.user.usecase.GetUserNicknameUseCase
import com.team23.domain.user.usecase.SetUserNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val getExistingNicknamesUseCase: GetExistingNicknamesUseCase,
	getUserNicknameUseCase: GetUserNicknameUseCase,
	private val setUserNicknameUseCase: SetUserNicknameUseCase,
) : ViewModel() {
	val nickname: StateFlow<String?> = getUserNicknameUseCase.invoke()
		.stateIn(viewModelScope, SharingStarted.Eagerly, null)

	fun onChangeLocalNickname(nickname: String) {
		viewModelScope.launch(Dispatchers.IO) {
			setUserNicknameUseCase.invoke(nickname)
		}
	}
}
