package com.team23.domain

import com.team23.domain.user.usecase.GetExistingNicknamesUseCase
import com.team23.domain.user.usecase.GetUserNicknameUseCase
import com.team23.domain.user.usecase.SetUserNicknameUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetUserNicknameUseCase)
    factoryOf(::GetExistingNicknamesUseCase)
    factoryOf(::SetUserNicknameUseCase)
}
